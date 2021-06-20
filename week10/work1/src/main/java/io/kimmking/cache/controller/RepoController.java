package io.kimmking.cache.controller;

import io.kimmking.cache.redission.RedissionService;
import io.kimmking.cache.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepoController {

    @Autowired
    RedissionService redissionService;

    @Autowired
    RepoService repoService;

    @RequestMapping("/repo/dec")
    void decGoods(String id) {
        repoService.decGoods(id);
    }

    @RequestMapping("/repo/init")
    void init(String id) {
        repoService.init(id);
    }
}
