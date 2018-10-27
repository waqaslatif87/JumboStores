package com.jumbo.stores.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.stores.dto.StoreDTO;
import com.jumbo.stores.dto.StoreLocationDTO;
import com.jumbo.stores.exception.JumboException;
import com.jumbo.stores.kdtree.KDNode;
import com.jumbo.stores.kdtree.KDTree;
import com.jumbo.stores.model.JumboSupermarket;
import com.jumbo.stores.model.Location;
import com.jumbo.stores.model.Store;
import com.jumbo.stores.model.StoreDistance;
import com.jumbo.stores.model.StoreDistanceComparator;
import com.jumbo.stores.utils.ModelMapper;
import com.jumbo.stores.validator.StoreLocationValidator;

/**
 * Implementation of {@link IJumboStoreService}.
 * 
 * @author Waqas
 *
 */
@Service
public class JumboStoreServiceImpl implements IJumboStoreService {
    private static final Logger logger = LogManager.getLogger(JumboStoreServiceImpl.class);
    @Value("${stores.json.file.path}")
    private String filePath;

    @Autowired
    private StoreLocationValidator storeLocationValidator;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Store> jumboStores;
    private Map<String, Store> storesMap;
    private KDNode rootNode;

    @Autowired
    private KDTree kdTree;

    @Autowired
    ModelMapper mapper;

    @PostConstruct
    public void initIt() throws UnsupportedEncodingException {

        logger.debug("Initialization started, Going to read stores json and build KDTree.");

        if (!Optional.ofNullable(filePath).isPresent()) {
            logger.error("Stores json file path is not configured.");
            throw new JumboException("Stores json file path is not configured.");
        }

        InputStreamReader is = new InputStreamReader(getClass().getResourceAsStream(filePath), "UTF-8");
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            JumboSupermarket jumboSupermarket = objectMapper.readerFor(new TypeReference<JumboSupermarket>() {
            }).readValue(is);

            logger.debug("Read and Deserialize Stores json file successfully.");

            jumboStores = jumboSupermarket.getStores();
            storesMap = new HashMap<String, Store>();
            jumboStores.stream().forEach(store -> storesMap.put(store.getUuid(), store));
            rootNode = kdTree.buildKDTree(jumboStores, 1);

            logger.debug("KDTree build successfully.");

        } catch (IOException e) {
            logger.error("Error occurred while reading the Stores JSON file and building the KDTree, {}",
                    e.getMessage(), e);
            throw new JumboException("failed to read and parse the json file.");
        }
    }

    @Override
    public List<StoreLocationDTO> getNearestStoresByLocation(Double latitude, Double longitude, Integer numNeighbour) {

        logger.debug("Going to find {} nearest stores for the given location ({},{})", numNeighbour, latitude,
                longitude);

        storeLocationValidator.validateLocation(latitude, longitude);

        if (rootNode == null) {
            logger.error(
                    "Error occurred while retrieving {} nearest neighbours for given Location ({},{})Root Node of KDTree is null.",
                    numNeighbour, latitude, longitude);

            throw new JumboException(
                    "Error occurred while retrieving " + numNeighbour + " nearest neighbours for given Location ("
                            + latitude.toString() + "," + longitude.toString() + ")Root Node of KDTree is null.");
        }

        StoreDistanceComparator comparator = new StoreDistanceComparator();
        TreeSet<StoreDistance> storesDistanceTreeSet = new TreeSet<>(comparator);
        Location loc = new Location(latitude, longitude);
        kdTree.getKNearestNeighbors(rootNode, loc, numNeighbour, storesDistanceTreeSet);

        logger.debug("Returning response for the {} nearest neighbours request.", numNeighbour);

        return storesDistanceTreeSet.stream().map(StoreDistance::getStore).map(store -> {
            return mapper.mapStoreToStoreLocationDTO(store);
        }).collect(Collectors.toList());

    }

    @Override
    public List<StoreLocationDTO> getAllStoresLocation() {

        if (!Optional.ofNullable(jumboStores).isPresent()) {
            logger.error("Exception Occurred while fetching all stores, Stores not found.");
            throw new JumboException("Exception Occurred while fetching all stores, Stores not found.");
        }

        return jumboStores.stream().map(store -> mapper.mapStoreToStoreLocationDTO(store)).collect(Collectors.toList());
    }

    @Override
    public StoreDTO getStoreDetails(String uuid) {

        if (!(Optional.ofNullable(storesMap).isPresent() && Optional.ofNullable(storesMap.get(uuid)).isPresent())) {
            logger.error("Store not found with store UUID : {}", uuid);
            throw new JumboException("Store not found with store UUID : " + uuid);
        }

        return mapper.mapStore2DTO(storesMap.get(uuid));
    }

}
