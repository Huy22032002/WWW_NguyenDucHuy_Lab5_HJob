package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "job_skill")
public class JobSkill {
    @EmbeddedId
    private JobSkillId id;

    @MapsId("jobId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private vn.edu.iuh.fit.models.Job job;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private vn.edu.iuh.fit.models.Skill skill;

    @Column(name = "more_infos", length = 1000)
    private String moreInfos;

    @Lob
    @Column(name = "skill_level", nullable = false)
    private String skillLevel;

    public JobSkillId getId() {
        return id;
    }

    public void setId(JobSkillId id) {
        this.id = id;
    }

    public vn.edu.iuh.fit.models.Job getJob() {
        return job;
    }

    public void setJob(vn.edu.iuh.fit.models.Job job) {
        this.job = job;
    }

    public vn.edu.iuh.fit.models.Skill getSkill() {
        return skill;
    }

    public void setSkill(vn.edu.iuh.fit.models.Skill skill) {
        this.skill = skill;
    }

    public String getMoreInfos() {
        return moreInfos;
    }

    public void setMoreInfos(String moreInfos) {
        this.moreInfos = moreInfos;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

}