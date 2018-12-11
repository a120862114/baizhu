package com.bzdepot.transport.service;

import com.bzdepot.transport.mapper.BlockGroupMapper;
import com.bzdepot.transport.mapper.BlockPriceMapper;
import com.bzdepot.transport.mapper.RegionBlockMapper;
import com.bzdepot.transport.mapper.RegionDefaultMapper;
import com.bzdepot.transport.model.*;
import com.bzdepot.transport.vo.BlockCityVo;
import com.bzdepot.transport.vo.BlockPriceVo;
import com.bzdepot.transport.vo.BlockVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlockService {

    private final static Logger loger = LoggerFactory.getLogger(BlockService.class);

    @Autowired
    private BlockPriceMapper blockPriceMapper;

    @Autowired
    private RegionBlockMapper regionBlockMapper;

   @Autowired
    private BlockGroupMapper blockGroupMapper;

   @Autowired
   private RegionDefaultMapper regionDefaultMapper;

    /**
     * 添加修改运费的配置信息
     * @param blockVo
     * @param blockCityVos
     * @param blockPriceVos
     * @return
     */
    @Transactional
    public int updateBlockData(BlockVo blockVo, List<BlockCityVo> blockCityVos, List<BlockPriceVo> blockPriceVos){
        int BlockStatus = 0;
        try {
            //开始更新区块城市数据
            Map<Integer,String> CityIdData = new HashMap<Integer, String>();
            Map<Integer,String> CityNameData = new HashMap<Integer, String>();
            Map<Integer,String> BlockAddId = new HashMap<Integer, String>();
            for(int a = 0;a < blockCityVos.size();a++){
                RegionBlock regionBlock = new RegionBlock();
                regionBlock.setSellerId(blockVo.getSellerId());
                regionBlock.setCompanyId(blockVo.getCompanyId());
                regionBlock.setRegionId(blockVo.getRegionId());
                regionBlock.setBlockId(blockCityVos.get(a).getBlockId());

                regionBlock.setCityId(blockCityVos.get(a).getCityId());
                regionBlock.setCityName(blockCityVos.get(a).getCityName());
                regionBlock.setIsSuspend(blockCityVos.get(a).getIsSuspend()); //暂停状态

                //判断当前数据为添加还是修改
                if(blockCityVos.get(a).getId() == null){
                    regionBlock.setCreateTime(new Date().getTime());
                    regionBlock.setUpdateTime(new Date().getTime());
                    BlockStatus = regionBlockMapper.insertSelective(regionBlock);
                }else{
                    regionBlock.setUpdateTime(new Date().getTime());
                    regionBlock.setId(blockCityVos.get(a).getId());
                    BlockStatus = regionBlockMapper.updateByPrimaryKeySelective(regionBlock);
                }
                if(BlockStatus == 0){
                    loger.error("区块城市信息添加失败:实体数据 - "+regionBlock.toString());
                    break;
                }
                if(!CityIdData.containsKey(blockCityVos.get(a).getBlockId())){
                    CityIdData.put(blockCityVos.get(a).getBlockId(),"");
                }
                if(!CityNameData.containsKey(blockCityVos.get(a).getBlockId())){
                    CityNameData.put(blockCityVos.get(a).getBlockId(),"");
                }
                if(!BlockAddId.containsKey(blockCityVos.get(a).getBlockId())){
                    BlockAddId.put(blockCityVos.get(a).getBlockId(),"");
                }
                CityNameData.put(blockCityVos.get(a).getBlockId(),CityNameData.get(blockCityVos.get(a).getBlockId())+","+blockCityVos.get(a).getCityName());
                try {
                    CityIdData.put(blockCityVos.get(a).getBlockId(),CityIdData.get(blockCityVos.get(a).getBlockId())+","+Long.toString(blockCityVos.get(a).getCityId()));
                }catch (Exception e){
                    loger.error("城市ID集合转码失败 - "+e.getMessage());
                    throw new RuntimeException("转码失败!");
                }
                try {
                    BlockAddId.put(blockCityVos.get(a).getBlockId(),BlockAddId.get(blockCityVos.get(a).getBlockId())+","+Long.toString(regionBlock.getId()));
                }catch (Exception e){
                    loger.error("区块城市ID集合转码失败 - "+e.getMessage());
                    throw new RuntimeException("转码失败!");
                }

            }

            if(BlockStatus == 0){
                throw new RuntimeException("更新区块城市数据失败!");
            }
            if(CityNameData.size() == 0){
                loger.error("城市名字字符串合计为长度为0: 数据 - "+CityNameData);
                throw new RuntimeException("获取失败!");
            }
            if(CityIdData.size() == 0){
                loger.error("城市ID字符串合计为长度为0: 数据 - "+CityIdData);
                throw new RuntimeException("获取失败!");
            }

            Long SellerId = blockVo.getSellerId();
            Long CompanyId = blockVo.getCompanyId();
            Long RegionId = blockVo.getRegionId();

            for (Integer key : CityNameData.keySet()) {
                int CountNums = blockGroupMapper.CountByBlockNode(SellerId,CompanyId,RegionId,key);
                BlockGroupWithBLOBs blockGroupWithBLOBs = new BlockGroupWithBLOBs();
                blockGroupWithBLOBs.setSellerId(SellerId);
                blockGroupWithBLOBs.setCompanyId(CompanyId);
                blockGroupWithBLOBs.setRegionId(RegionId);
                blockGroupWithBLOBs.setBlockId(key);
                String CityIds = CityIdData.get(key).substring(1);
                String CityNames = CityNameData.get(key).substring(1);
                String AddId = BlockAddId.get(key).substring(1);
                blockGroupWithBLOBs.setCityids(CityIds);
                blockGroupWithBLOBs.setCityname(CityNames);
                blockGroupWithBLOBs.setPid(AddId);

                if(CountNums == 0){
                    BlockStatus = blockGroupMapper.insertSelective(blockGroupWithBLOBs);
                }else{
                    BlockStatus = blockGroupMapper.updateBlockDatas(blockGroupWithBLOBs);
                }
                if(BlockStatus == 0){
                    loger.error("城市区块映射组添加失败 - "+blockGroupWithBLOBs.toString());
                    break;
                }
            }
            if(BlockStatus == 0){
                throw new RuntimeException("更新区块城市映射数据失败!");
            }
            //结束更新区块城市数据

            //开始更新区块价格配置
            for(int b = 0; b < blockPriceVos.size(); b++){
                BlockPrice blockPrice = new BlockPrice();
                blockPrice.setSellerId(blockVo.getSellerId());
                blockPrice.setCompanyId(blockVo.getCompanyId());
                blockPrice.setRegionId(blockVo.getRegionId());
                blockPrice.setBlockId(blockPriceVos.get(b).getBlockId());

                blockPrice.setWeightStart(blockPriceVos.get(b).getWeightStart());
                blockPrice.setWeightEnd(blockPriceVos.get(b).getWeightEnd());
                blockPrice.setStartPrice(blockPriceVos.get(b).getStartPrice());
                blockPrice.setEndPrice(blockPriceVos.get(b).getEndPrice());
                blockPrice.setCountType(blockPriceVos.get(b).getCountType());

                if(blockPriceVos.get(b).getId() == null){
                    BlockStatus = blockPriceMapper.insertSelective(blockPrice);
                }else {
                    blockPrice.setId(blockPriceVos.get(b).getId());
                    BlockStatus = blockPriceMapper.updateByPrimaryKeySelective(blockPrice);
                }
                if(BlockStatus == 0){
                    loger.error("区块价格配置添加失败 - "+blockPrice.toString());
                    break;
                }
            }
            if(BlockStatus == 0){
                throw new RuntimeException("更新区块价格配置失败");
            }
            //结束更新区块价格配置
        }catch (Exception e){
            BlockStatus = 0;
            loger.error("事务回滚 - "+e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //事务回滚
        }
        return BlockStatus;
    }

    /**
     * 删除价格配置数据
     * @param id
     * @return
     */
    public int delBlockPriceById(Long id){
        return blockPriceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取所有级联数据
     * @param blockGroupData
     * @return
     */
    public List<BlockGroupData> findAllBySidCidRid(BlockGroupData blockGroupData){
        List<BlockGroupData> datas = blockGroupMapper.findGroupAndPrice(blockGroupData);
        return datas;
    }

    /**
     * 更新默认区域
     * @param regionDefault
     * @return
     */
    public int updateRegionDefault(RegionDefault regionDefault){
        int Status = 0;
        if(regionDefault.getId() == null){
            Status = regionDefaultMapper.insertSelective(regionDefault);
        }else{
            Status = regionDefaultMapper.updateByPrimaryKeySelective(regionDefault);
        }
        return Status;
    }

    /**
     * 获取快递公司默认的区域的城市数据
     * @param companyId
     * @param sellerId
     * @return
     */
    public List<RegionBlock> getCompanyDefaultCity(Long companyId,Long sellerId){
        RegionBlock maps = new RegionBlock();
        maps.setCompanyId(companyId);
        maps.setSellerId(sellerId);
        return regionBlockMapper.getCityDataByCompanyIdAndSellerId(maps);
    }

    /**
     * 获取快递的城市的运费配置
     * @param companyId
     * @param sellerId
     * @param cityId
     * @return
     */
    public BlockPrice findDefaultCityConfig(Long companyId,Long sellerId, Long cityId){
        System.out.println(companyId);
        System.out.println(sellerId);
        System.out.println(cityId);
        System.out.println(cityId);
        BlockPrice blockPrice = blockPriceMapper.findCityConfig(companyId,sellerId,cityId);
        System.out.println(blockPrice);
        return blockPrice;
    }

    /**
     * 计算是拆包
     * @param weightStart
     * @param weightEnd
     * @param totalWeight
     * @return
     */
    public BigDecimal[] Dismantling(BigDecimal weightStart,BigDecimal weightEnd,BigDecimal totalWeight){
        BigDecimal Weight = weightEnd.subtract(new BigDecimal(1)); //减去皮的重量1公斤
        System.out.println(Weight);
        BigDecimal[] bagarr = totalWeight.divideAndRemainder(Weight); //获取拆多少包与剩下多少斤
        System.out.println(bagarr[0]);
        System.out.println(bagarr[1]);
        if(bagarr.length >= 2){
            return bagarr;
        }
        return null;
    }

    /**
     * 计算价格
     * @param Dismantling
     * @param blockPrice
     * @return
     */
    public BigDecimal CalculatingPrice(BigDecimal[] Dismantling,BlockPrice blockPrice){
        int BagNums = Dismantling[0].intValue();
        BigDecimal Weight = blockPrice.getWeightEnd().subtract(new BigDecimal(1)); //减去皮的重量1公斤
        System.out.println(BagNums);
        BigDecimal Money = new BigDecimal(0);
        //计算每包的价格
        for(int i = 0; i < BagNums; i++){
            //判断算法
            BigDecimal One;
            if(blockPrice.getCountType().equals(new Byte("0"))){
               One = Weight.subtract(new BigDecimal(1));
            }else{
               One = Weight.subtract(blockPrice.getWeightStart());
            }
            BigDecimal Two = One.multiply(blockPrice.getEndPrice());
            BigDecimal Three = Two.add(blockPrice.getStartPrice());
            Money = Money.add(Three);
        }
        System.out.println(Money);
        Money = Money.add(this.CalculatingPriceNo(Dismantling[1],blockPrice));
        System.out.println(Money);
        return Money;
    }

    /**
     * 计算不拆包的价格
     * @param Weight
     * @param blockPrice
     * @return
     */
    public BigDecimal CalculatingPriceNo(BigDecimal Weight,BlockPrice blockPrice){
        //如果重量大于首重则计算否则直接返回首重价格
        if(Weight.compareTo(blockPrice.getWeightStart()) == 1){
            BigDecimal Money = new BigDecimal(0);
            BigDecimal One;
            if(blockPrice.getCountType().equals(new Byte("0"))){
                One = Weight.subtract(new BigDecimal(1));
            }else{
                One = Weight.subtract(blockPrice.getWeightStart());
            }
            BigDecimal Two = One.multiply(blockPrice.getEndPrice());
            BigDecimal Three = Two.add(blockPrice.getStartPrice());
            Money = Money.add(Three);
            return Money;
        }else{
            //直接返回首重价格
            return blockPrice.getStartPrice();
        }
    }

    /**
     * 更新省市是否暂停发货
     * @param rBlcokId
     * @param isSuspend
     * @return
     */
    public int updateIsSuspend(Long rBlcokId,Byte isSuspend){
        RegionBlock regionBlock = new RegionBlock();
        regionBlock.setId(rBlcokId);
        regionBlock.setIsSuspend(isSuspend);
        return regionBlockMapper.updateByPrimaryKeySelective(regionBlock);
    }

    /**
     * 查询当前商家的快递是否配置了城市数据列表
     * @param sellerId
     * @param companyId
     * @return
     */
    public List<BlockGroup> findComanyForCitysData(Long sellerId,Long companyId){
        return blockGroupMapper.selectBySellerIdAndCompanyId(sellerId,companyId);
    }
}
