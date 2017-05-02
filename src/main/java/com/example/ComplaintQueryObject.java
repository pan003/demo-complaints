/**
 * @copyright: Copyright (c) 2015-2020 jd.com All Rights Reserved
 * @file: ComplaintQueryObject.java project: demo-complaints
 * @creator: pan
 * @date: 17/5/2
 */


package com.example;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @description:
 * @see:
 * @author: pan
 * @version: 1.0
 * @createdate: 2017-05-02 11:00
 * @lastdate:
 */
@Entity
public class  ComplaintQueryObject {
    @Id
    private String id;
    private String company;
    private String descripition;

    public ComplaintQueryObject() {
    }

    public ComplaintQueryObject(String id, String company, String descripition) {
        this.id = id;
        this.company = company;
        this.descripition = descripition;
    }

    public String getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getDescripition() {
        return descripition;
    }
}
