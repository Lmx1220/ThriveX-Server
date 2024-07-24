package liuyuyang.net.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liuyuyang.net.execption.GuiguException;
import liuyuyang.net.model.Swiper;
import liuyuyang.net.result.Result;
import liuyuyang.net.service.SwiperService;
import liuyuyang.net.utils.Paging;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "轮播图管理")
@RestController
@RequestMapping("/swiper")
@Transactional
public class SwiperController {
    @Resource
    private SwiperService swiperService;

    @PostMapping
    @ApiOperation("新增轮播图")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 1)
    public Result<String> add(@RequestBody Swiper swiper) {
        try {
            boolean res = swiperService.save(swiper);

            return res ? Result.success() : Result.error();
        } catch (Exception e) {
            throw new GuiguException(400, e.getMessage());
        }
    }

    @DeleteMapping("/{cid}")
    @ApiOperation("删除轮播图")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 2)
    public Result<String> del(@PathVariable Integer cid) {
        Swiper data = swiperService.getById(cid);
        if (data == null) return Result.error("该数据不存在");

        Boolean res = swiperService.removeById(cid);

        return res ? Result.success() : Result.error();
    }

    @DeleteMapping("/batch")
    @ApiOperation("批量删除轮播图")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 3)
    public Result batchDel(@RequestBody List<Integer> cids) {
        Boolean res = swiperService.removeByIds(cids);

        return res ? Result.success() : Result.error();
    }

    @PatchMapping
    @ApiOperation("编辑轮播图")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 4)
    public Result<String> edit(@RequestBody Swiper swiper) {
        try {
            boolean res = swiperService.updateById(swiper);

            return res ? Result.success() : Result.error();
        } catch (Exception e) {
            throw new GuiguException(400, e.getMessage());
        }
    }

    @GetMapping("/{cid}")
    @ApiOperation("获取轮播图")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 5)
    public Result<Swiper> get(@PathVariable Integer cid) {
        Swiper data = swiperService.getById(cid);
        return Result.success(data);
    }

    @GetMapping
    @ApiOperation("获取轮播图列表")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 6)
    public Result<List<Swiper>> list() {
        List<Swiper> data = swiperService.list();
        return Result.success(data);
    }

    @GetMapping("/{page}/{size}")
    @ApiOperation("分页查询轮播图列表")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 7)
    public Result list(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Swiper> data = swiperService.list(page, size);

        Map<String, Object> result = Paging.filter(data);

        return Result.success(result);
    }
}
