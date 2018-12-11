package com.bzdepot.transport.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.transport.mapper.TransportCompanyMapper;
import com.bzdepot.transport.mapper.TransportRegionMapper;
import com.bzdepot.transport.model.TransportCompany;
import com.bzdepot.transport.model.TransportRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
public class TransportCompanyService {

    private final static Logger loger = LoggerFactory.getLogger(TransportCompanyService.class);

    @Autowired
    private TransportCompanyMapper transportCompanyMapper;

    @Autowired
    private TransportRegionMapper transportRegionMapper;

    /**
     * 获取所属运送类型的所属子数据
     * @param TypeId
     * @return
     */
    public List<TransportCompany> getList(Long TypeId){
        return transportCompanyMapper.selectByTypeId(TypeId,UserUtil.getId());
    }

    /**
     * 获取运送的区域划分
     * @param Companyid
     * @return
     */
    public List<TransportRegion> getBlockListDataByCid(Long SellerId,Long Companyid){
        return transportRegionMapper.findBlockListByCid(SellerId,Companyid);
    }

    /**
     * 获取有城市的公司数据
     * @param typeId
     * @return
     */
    public List<TransportCompany> getHasCitysForCompany(Long typeId,Long sellerId){
        TransportCompany maps = new TransportCompany();
        maps.setTypeId(typeId);
        maps.setSellerId(sellerId);
        return transportCompanyMapper.findHasCityData(maps);
    }

    /**
     * 更新快递公司
     * @param transportCompany
     * @return
     */
    @Transactional
    public int updateCompany(TransportCompany transportCompany){
        int Ok = 0;
        try {
            int isAdd = 0;
            if (transportCompany.getId() == null) {
                transportCompany.setTypeId(1l);
                transportCompany.setCreateTime(new Date().getTime());
                transportCompany.setUpdateTime(new Date().getTime());
                Ok = transportCompanyMapper.insertSelective(transportCompany);
                isAdd = 1;
            } else {
                transportCompany.setUpdateTime(new Date().getTime());
                Ok = transportCompanyMapper.updateByPrimaryKeySelective(transportCompany);
                isAdd = 0;
            }
            if(Ok == 0){
                throw new RuntimeException("更新运输公司数据失败!");
            }

            if(isAdd > 0){
                Ok = this.updateRegion(transportCompany.getId(),transportCompany.getSellerId());
                if(Ok == 0){
                    throw new RuntimeException("更新运输公司区域数据失败!");
                }
            }
        }catch (Exception e){
            loger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Ok;
    }

    /**
     * 添加运输公司的默认区域
     * @param comanyId
     * @return
     */
    @Transactional
    public int updateRegion(Long comanyId,Long sellerId){
        System.out.println(comanyId);
        int Ok = 0;
        TransportRegion transportRegions = new TransportRegion();
        transportRegions.setCompanyId(comanyId);
        transportRegions.setSellerId(sellerId);
        try {
            for(int i = 1; i < 16; i++){
                transportRegions.setDescribe("全国划分为"+String.valueOf(i)+"个区域");
                transportRegions.setBlockNums(Integer.valueOf(i));
                Ok = transportRegionMapper.insertSelective(transportRegions);
                if(Ok == 0){
                    break;
                }
            }
            if(Ok == 0){
                throw new RuntimeException("更新区域失败!");
            }
        }catch (Exception e){
            loger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Ok;
    }

    /**
     * 获取商家自己的运输公司数据
     * @return
     */
    public List<TransportCompany> findSelfComany(){
        return transportCompanyMapper.findSelfComany(UserUtil.getId());
    }

    /**
     * 删除快递数据
     * @param id
     * @return
     */
    public int deleteCompanyData(Long id){
        return transportCompanyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询单条的快递数据
     * @param id
     * @return
     */
    public TransportCompany findOneComanyById(Long id){
        return transportCompanyMapper.selectByPrimaryKey(id);
    }
}
