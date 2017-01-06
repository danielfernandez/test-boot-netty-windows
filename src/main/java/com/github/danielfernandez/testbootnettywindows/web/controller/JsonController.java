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
package com.github.danielfernandez.testbootnettywindows.web.controller;

import com.github.danielfernandez.testbootnettywindows.business.Entity;
import com.github.danielfernandez.testbootnettywindows.business.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
public class JsonController {


    private EntityRepository entityRepository = null;


    public JsonController() {
        super();
    }


    @Autowired
    public void setEntityRepository(final EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }


    @RequestMapping("/index")
    public String index() {
        return "Use '/list/{size}' being 'size' the number of elements desired";
    }


    @RequestMapping("/list/{size}")
    public Flux<Entity> list(@PathVariable("size") final int size) {
        return this.entityRepository.findEntities(Math.max(size, 1));
    }




}
