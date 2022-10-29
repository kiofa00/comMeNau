/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Session;

import java.util.List;

/**
 *
 * @author PC
 */
public class SessionDTO {
    private String day;
    private List<Timeline> timeline;
    private String code;
    private String fromto;
    public SessionDTO() {
    }

    public SessionDTO(String day, String code, String fromto) {
        this.day = day;
        this.code = code;
        this.fromto = fromto;
    }
    public SessionDTO(String day, List<Timeline> timeline) {
        this.day = day;
        this.timeline = timeline;
    }

    public int getTimelineSize(){
        return timeline.size();
    }
    

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Timeline> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<Timeline> timeline) {
        this.timeline = timeline;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }
    
}
