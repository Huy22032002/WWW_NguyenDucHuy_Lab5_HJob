package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "job_application")
public class JobApplication {
    @Id
    @Column(name = "application_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "can_id", nullable = false)
    private vn.edu.iuh.fit.models.Candidate can;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private vn.edu.iuh.fit.models.Job job;

    @Column(name = "application_date", nullable = false)
    private Date applicationDate;

    @ColumnDefault("'PENDING'")
    @Lob
    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public vn.edu.iuh.fit.models.Candidate getCan() {
        return can;
    }

    public void setCan(vn.edu.iuh.fit.models.Candidate can) {
        this.can = can;
    }

    public vn.edu.iuh.fit.models.Job getJob() {
        return job;
    }

    public void setJob(vn.edu.iuh.fit.models.Job job) {
        this.job = job;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}