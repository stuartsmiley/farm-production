package com.finca_la_caprichosa.service;

import com.finca_la_caprichosa.data.GraphRepository;
import com.finca_la_caprichosa.model.Goat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by ssmiley on 12/14/17.
 */
@Stateless
public class GraphService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphService.class);

    @Inject
    private GraphRepository graphRepository;

    public List<Goat> fetchGoats() {
        List<Goat> goats = graphRepository.findGoats();
        LOGGER.info("Found goats: {}", goats.size());
        return goats;
    }
}
