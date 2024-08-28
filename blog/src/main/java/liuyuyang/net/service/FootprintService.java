package liuyuyang.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import liuyuyang.net.model.Footprint;
import liuyuyang.net.vo.FilterVo;

import java.util.List;

public interface FootprintService extends IService<Footprint> {
    public List<Footprint> list(FilterVo filterVo);
}
