//package com.yyh.selenium_medi.controller;
//
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.api.ApiController;
//import com.baomidou.mybatisplus.extension.api.R;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.yyh.selenium_medi.entity.OutputEntity;
//import com.yyh.selenium_medi.service.OutputEntityService;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.io.Serializable;
//import java.util.List;
//
///**
// * (OutputEntity)表控制层
// *
// * @author makejava
// * @since 2023-11-15 17:35:16
// */
//@RestController
//@RequestMapping("outputEntity")
//public class OutputEntityController extends ApiController {
//    /**
//     * 服务对象
//     */
//    @Resource
//    private OutputEntityService outputEntityService;
//
//    /**
//     * 分页查询所有数据
//     *
//     * @param page 分页对象
//     * @param outputEntity 查询实体
//     * @return 所有数据
//     */
//    @GetMapping
//    public R selectAll(Page<OutputEntity> page, OutputEntity outputEntity) {
//        return success(this.outputEntityService.page(page, new QueryWrapper<>(outputEntity)));
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public R selectOne(@PathVariable Serializable id) {
//        return success(this.outputEntityService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param outputEntity 实体对象
//     * @return 新增结果
//     */
//    @PostMapping
//    public R insert(@RequestBody OutputEntity outputEntity) {
//        return success(this.outputEntityService.save(outputEntity));
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param outputEntity 实体对象
//     * @return 修改结果
//     */
//    @PutMapping
//    public R update(@RequestBody OutputEntity outputEntity) {
//        return success(this.outputEntityService.updateById(outputEntity));
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param idList 主键结合
//     * @return 删除结果
//     */
//    @DeleteMapping
//    public R delete(@RequestParam("idList") List<Long> idList) {
//        return success(this.outputEntityService.removeByIds(idList));
//    }
//}
//
