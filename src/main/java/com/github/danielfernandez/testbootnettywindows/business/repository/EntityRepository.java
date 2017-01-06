/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package com.github.danielfernandez.testbootnettywindows.business.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.danielfernandez.testbootnettywindows.business.Entity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class EntityRepository {



    public EntityRepository() {
        super();
    }


    public Flux<Entity> findEntities(final int size) {

        final List<Entity> entities = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            entities.add(new Entity(UUID.randomUUID().toString()));
        }

        return Flux.fromIterable(entities);

    }




}
