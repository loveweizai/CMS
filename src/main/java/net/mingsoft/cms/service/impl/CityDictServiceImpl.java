package net.mingsoft.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import net.mingsoft.cms.mapper.CityDictMapper;
import net.mingsoft.cms.model.CityDict;
import net.mingsoft.cms.service.CityDictService;
import net.mingsoft.cms.util.WebServiceUtil;

@Service
public class CityDictServiceImpl implements CityDictService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${project.webService.url}")
	private String projectWebServiceUrl;
	
	@Value("${project.webService.getAreaList}")
	private String getAreaist;

    @Autowired
    private CityDictMapper cityDictMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return cityDictMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert() {
        // TODO Auto-generated method stub
    	List<CityDict> cityDictList = getCityDictByJson();
    	int sum = 0;
		for(CityDict cityDict:cityDictList) {
			CityDict en = new CityDict();
			en.setId(cityDict.getId());
			try {
				en = cityDictMapper.selectByPrimaryKey(cityDict.getId());
				if(en == null) {
					int count = cityDictMapper.insertSelective(cityDict);
					if(count>0) {
						sum++;
					}
				}else {
					cityDict.setId(en.getId());
					int count = cityDictMapper.updateByPrimaryKeySelective(cityDict);
					if(count>0) {
						sum++;
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("插入或更新异常：{}",e);
			}
			
		}
		return sum;
    }
    
	public List<CityDict> getCityDictByJson() {
		String result = WebServiceUtil.getProjectInfoResultByWebService(projectWebServiceUrl, getAreaist, null);
		List<CityDict> cityDictList = new ArrayList<>();
		if(StringUtil.isNotEmpty(result)) {
			JSONArray jsonArray = JSONUtil.parseArray(result);
			for(Object obj:jsonArray) {
		        JSONObject jsonObject=JSONUtil.parseObj(obj);
		        CityDict cityDict=JSONUtil.toBean(jsonObject, CityDict.class);
				cityDictList.add(cityDict);
			}
		}
		return cityDictList;
	}

    @Override
    public int insertSelective(CityDict record) {
        // TODO Auto-generated method stub
        return cityDictMapper.insertSelective(record);
    }


    @Override
    public int updateByPrimaryKeySelective(CityDict record) {
        // TODO Auto-generated method stub
        return cityDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CityDict record) {
        // TODO Auto-generated method stub
        return cityDictMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CityDict> listBySelective(CityDict record) {
        // TODO Auto-generated method stub
        return cityDictMapper.listBySelective(record);
    }

    @Override
    public int deleteBatchBySelective(List<CityDict> record) {
        // TODO Auto-generated method stub
        return cityDictMapper.deleteBatchBySelective(record);
    }
}