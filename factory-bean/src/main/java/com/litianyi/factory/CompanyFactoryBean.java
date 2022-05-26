package com.litianyi.factory;

import com.litianyi.pojo.Company;
import org.springframework.beans.factory.FactoryBean;

import java.util.UUID;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/26 3:29 PM
 */
public class CompanyFactoryBean implements FactoryBean<Company> {

    //公司名称,地址,规模
    private final String companyInfo;

    public CompanyFactoryBean(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    @Override
    public Company getObject() throws Exception {
        String[] info = companyInfo.split(",");
        Company company = new Company();
        company.setName(info[0]);
        company.setAddress(info[1]);
        company.setScale(Integer.parseInt(info[2]));

        return company;
    }

    @Override
    public Class<?> getObjectType() {
        return Company.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
