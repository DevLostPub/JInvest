package com.devlost.jinvest.bean;

import com.devlost.jinvest.dao.RiskDAO;
import com.devlost.jinvest.dao.CategoryDAO;
import com.devlost.jinvest.dao.FundDAO;
import com.devlost.jinvest.model.Category;
import com.devlost.jinvest.model.Fund;
import com.devlost.jinvest.model.Risk;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author lucas
 */
@ViewScoped
@ManagedBean(name = "funds")
public class FundsBean {

    private ArrayList<Fund> items;
    private ArrayList<Category> categoryList;
    private ArrayList<Risk> riskList;
    private Fund selectedItem;
    private Long selectedId = null;
    private BigDecimal fundVariation = BigDecimal.ZERO;
    private BigDecimal fundTotalValue = BigDecimal.ZERO;

    private String operationType = null;
    private static final Logger log = Logger.getLogger(FundsBean.class.getName());

    @PostConstruct
    private void init() {
        try {
            items = new FundDAO().getAll();
        } catch (Exception e) {
            items = new ArrayList<>();
        }
    }

    /**
     * *
     * Creates a new object to hold a fund
     */
    public void prepareNew() {
        log.info("prepare new Item");
        selectedItem = new Fund();
        selectedItem.setRisk(new Risk());
        selectedItem.setCategory(new Category());
    }

    /**
     * *
     * Add or update an item on the database, this view related method requires
     * an operation type to be set before the call The operation types are
     * 'create' and 'update'
     */
    public void saveItem() {
        if (operationType.equals("create")) {
            createItem();
        } else if (operationType.equals("update")) {
            updateItem();
        }
    }

    private void createItem() {
        selectedItem.setLocked(false);
        new FundDAO().create(selectedItem);
    }

    public void updateItem() {
        new FundDAO().update(selectedItem);
    }

    public void removeItem() {
        new FundDAO().remove(selectedItem);
        items = new FundDAO().getAll();
    }

    /**
     * *
     * Locks and unlocks the fund state
     */
    public void changeStatus() {
        selectedItem.setLocked(!selectedItem.getLocked());
        new FundDAO().update(selectedItem);
    }

    public ArrayList<Fund> getItems() {
        return items;
    }

    public void fundInfo() {
        categoryList = new CategoryDAO().getAll();
        riskList = new RiskDAO().getAll();
        if (selectedId == null) {
            prepareNew();
        } else {
            selectedItem = new FundDAO().get(selectedId);
        }
    }

    public void updateFundValue() {
        if (fundTotalValue.compareTo(selectedItem.getTotalNetAsset()) != 0) {
            selectedItem.setTotalNetAsset(fundTotalValue);
            new FundDAO().update(selectedItem);
        }
    }

    public void calculateFieldFundValue() {
        if (fundVariation.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal hundred = new BigDecimal(100);
            fundTotalValue = selectedItem.getTotalNetAsset();
            BigDecimal temp = fundTotalValue.multiply(fundVariation.divide(hundred));
            fundTotalValue = fundTotalValue.add(temp);
        } else {
            fundTotalValue = selectedItem.getTotalNetAsset();
        }
    }

    public Fund getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Fund selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public ArrayList<Risk> getRiskList() {
        return riskList;
    }

    public void setRiskList(ArrayList<Risk> riskList) {
        this.riskList = riskList;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getFundVariation() {
        return fundVariation;
    }

    public void setFundVariation(BigDecimal fundVariation) {
        this.fundVariation = fundVariation;
    }

    public BigDecimal getFundTotalValue() {
        return fundTotalValue;
    }

    public void setFundTotalValue(BigDecimal fundTotalValue) {
        this.fundTotalValue = fundTotalValue;
    }

}
