package com.example.hp.demo.constant;

import java.util.Map;

public abstract class BasePcsDetail {

    protected String[] m_slistPCSName;

    protected Map<String, String> pcsCodeMap;
    protected Map<String, String> dept2jgidMap;

    public abstract String getPCSCode(String spcsName);

    public abstract String tranDept2Jgid(String dept);

    public abstract String[] getPCSNameDetail();

}
