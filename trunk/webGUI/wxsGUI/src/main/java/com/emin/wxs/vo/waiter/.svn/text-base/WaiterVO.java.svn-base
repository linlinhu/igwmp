package com.emin.wxs.vo.waiter;

import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.wxs.vo.restaurant.RestaurantVO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WaiterVO {
    private Long Id;

    /**
     * 服务员姓名
     */
    private String name;
    /**
     * 服务员性别
     */
    private String gender;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 现有积分
     */
    private int integral;

    /**
     *  申请时间
     */
    private Long createDate;

    /**
     * 历史积分
     */
    private int historyIntegral;
    /**
     * 兑换积分
     */
    private int exchangeIntegral;
    /**
     * 关联餐厅信息
     */
    private List<RestaurantVO> restaurantVO = new ArrayList<RestaurantVO>();

    private int auditingStatus;//审核状态
    private String auditingPerson;//审核人
    private String memo;//审核备注


    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getHistoryIntegral() {
        return historyIntegral;
    }

    public void setHistoryIntegral(int historyIntegral) {
        this.historyIntegral = historyIntegral;
    }

    public int getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setExchangeIntegral(int exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    public List<RestaurantVO> getRestaurantVO() {
        return restaurantVO;
    }

    public void setRestaurantVO(List<RestaurantVO> restaurantVO) {
        this.restaurantVO = restaurantVO;
    }

    public int getAuditingStatus() {
        return auditingStatus;
    }

    public void setAuditingStatus(int auditingStatus) {
        this.auditingStatus = auditingStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAuditingPerson() {
        return auditingPerson;
    }

    public void setAuditingPerson(String auditingPerson) {
        this.auditingPerson = auditingPerson;
    }

    public static WaiterVO convertPoToVo(RestaurantServantInfo servantInfo, int cascadeDepth) {
        if (servantInfo == null) return null;
        WaiterVO waitVo = new WaiterVO();

        waitVo.setId(servantInfo.getId());
        waitVo.setName(servantInfo.getName());
        waitVo.setCreateDate(servantInfo.getCreateTime());
        waitVo.setGender(String.valueOf(servantInfo.getGender()));
        waitVo.setIntegral(servantInfo.getIntegral());
        waitVo.setHistoryIntegral(servantInfo.getHistoryIntegral());
        waitVo.setExchangeIntegral(servantInfo.getExchangeIntegral());
        waitVo.setTelephone(servantInfo.getCellphone());
        waitVo.setAuditingPerson(servantInfo.getAuditingPerson());
        waitVo.setAuditingStatus(servantInfo.getAuditingStatus());
        waitVo.setMemo(servantInfo.getAuditingMemo());
        if (cascadeDepth > 0) {
            cascadeDepth--;
            List<RestaurantVO> restaurantVos = new ArrayList<RestaurantVO>();
            if (servantInfo.getRestaurants() != null && servantInfo.getRestaurants().size() > 0) {
                for (RestaurantPublicInfo restInfo : servantInfo.getRestaurants()) {
                    restaurantVos.add(RestaurantVO.convertPoToVo(restInfo, cascadeDepth));
                }
            }
            waitVo.setRestaurantVO(restaurantVos);
        }
        return waitVo;
    }

    public static RestaurantServantInfo convertVoToPo(RestaurantServantInfo restaurantServantInfo, WaiterVO waiterVo, int cascadeDepth) {

        RestaurantServantInfo po = null;
        if (waiterVo == null) return null;
        if (restaurantServantInfo != null) {
            po = restaurantServantInfo;
        } else {
            po = new RestaurantServantInfo();
            po.setCreateTime(System.currentTimeMillis());
        }

        po.setId(waiterVo.getId());
        po.setLastModifyTime(System.currentTimeMillis());
        po.setStatus(1);
        po.setCellphone(waiterVo.getTelephone());
        po.setName(waiterVo.getName());
        po.setIntegral(waiterVo.getIntegral());
        po.setHistoryIntegral(waiterVo.getHistoryIntegral());
        po.setExchangeIntegral(waiterVo.getExchangeIntegral());
        po.setAuditingPerson(waiterVo.getAuditingPerson());
        po.setAuditingStatus(waiterVo.getAuditingStatus());
        po.setAuditingMemo(waiterVo.getMemo());
        if (StringUtils.isNotBlank(waiterVo.getGender())) {
            po.setGender(Integer.valueOf(waiterVo.getGender()));
        }

        Set<RestaurantPublicInfo> rstPos = new HashSet<RestaurantPublicInfo>();
        if (cascadeDepth > 0) {
            cascadeDepth--;
            if (waiterVo.getRestaurantVO() != null && waiterVo.getRestaurantVO().size() > 0) {
                List<RestaurantVO> restVos = waiterVo.getRestaurantVO();
                for (RestaurantVO vo : restVos) {
                    rstPos.add(RestaurantVO.convertVoToPo(null, vo, cascadeDepth));
                }
            }
            po.setRestaurants(rstPos);
        }
        return po;
    }

}
