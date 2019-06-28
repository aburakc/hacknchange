package tr.com.havelsan.hacknchange.hackreka.plugin.impl;

public enum EnumIntegration {
    EVRAKA("evraka", "Evraka Entegrasyon", "https://www.evraka.com"),
    EONAY("eonay", "E Onay Entegrasyon", "https://www.eonay.com"),
    JIRA("jira", "JIRA Entegrasyon", "https://www.jira.com"),
    TFS("tfs", "TFS Entegrasyon", "https://www.tfs.com"),
    EMAIL("email", "EMAIL Entegrasyon", "https://www.email.com"),
    KALITE("kalite", "Kalite Sistemi Entegrasyon", "https://www.kalite.com"),
    SEYAHAT("seyahat", "Seyahat Bilgileri Entegrasyon", "https://www.seyahat.com"),
    IZIN("izin", "İzin Talepleri Entegrasyon", "https://www.izin.com"),
    SATINALMA("satinalma", "Satın Alma Talepleri Entegrasyon", "https://www.satinalma.com"),
    TAKVIM("takvim", "Toplantılar", "https://calendar.google.com/");
    String code;
    String name;
    String link;

    EnumIntegration(String code, String name, String link){
        this.code = code;
        this.name = name;
        this.link = link;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
