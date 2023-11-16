//package com.yyh.selenium_medi.controller;
//
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.api.ApiController;
//import com.baomidou.mybatisplus.extension.api.R;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.yyh.selenium_medi.entity.InputEntity;
//import com.yyh.selenium_medi.service.InputEntityService;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.io.Serializable;
//import java.util.List;
//
///**
// * (InputEntity)表控制层
// *
// * @author makejava
// * @since 2023-11-15 17:34:39
// */
//@RestController
//@RequestMapping("inputEntity")
//public class InputEntityController extends ApiController {
//    /**
//     * 服务对象
//     */
//    @Resource
//    private InputEntityService inputEntityService;
//
//    /**
//     * 分页查询所有数据
//     *
//     * @param page 分页对象
//     * @param inputEntity 查询实体
//     * @return 所有数据
//     */
//    @GetMapping
//    public R selectAll(Page<InputEntity> page, InputEntity inputEntity) {
//        return success(this.inputEntityService.page(page, new QueryWrapper<>(inputEntity)));
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
//        return success(this.inputEntityService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param inputEntity 实体对象
//     * @return 新增结果
//     */
//    @PostMapping
//    public R insert(@RequestBody InputEntity inputEntity) {
//        return success(this.inputEntityService.save(inputEntity));
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param inputEntity 实体对象
//     * @return 修改结果
//     */
//    @PutMapping
//    public R update(@RequestBody InputEntity inputEntity) {
//        return success(this.inputEntityService.updateById(inputEntity));
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
//        return success(this.inputEntityService.removeByIds(idList));
//    }
//}
//
