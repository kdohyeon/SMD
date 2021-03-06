package org.apromore.dao.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * ProcessModelVersion generated by hbm2java
 */
@Entity
@Table(name = "process_model_version")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Configurable("processModelVersion")
public class ProcessModelVersion implements Serializable {

    /**
     * Hard coded for interoperability.
     */
    private static final long serialVersionUID = -1172538404638485548L;

    private Integer processModelVersionId;
    private String rootFragmentVersionId;
    private Integer versionNumber;
    private String versionName;
    private Integer changePropagation;
    private Integer lockStatus;
    private Integer numVertices;
    private Integer numEdges;

    private ProcessBranch processBranch;
    private Set<Native> natives = new HashSet<Native>(0);
    private Set<Node> parentProcesses = new HashSet<Node>(0);
    private Set<ProcessFragmentMap> processFragmentMaps = new HashSet<ProcessFragmentMap>(0);
    private Set<ProcessBranch> processBranchesForCurrentProcessModelVersionId = new HashSet<ProcessBranch>(0);
    private Set<ProcessBranch> processBranchesForSourceProcessModelVersionId = new HashSet<ProcessBranch>(0);

    private Set<ProcessModelAttribute> processModelAttributes = new HashSet<ProcessModelAttribute>(0);
    private Set<ObjectType> objectTypes = new HashSet<ObjectType>(0);
    private Set<ResourceType> resourceTypes = new HashSet<ResourceType>(0);



    /**
     * Default Constructor.
     */
    public ProcessModelVersion() {
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "process_model_version_id", unique = true, nullable = false)
    public Integer getProcessModelVersionId() {
        return this.processModelVersionId;
    }

    public void setProcessModelVersionId(final Integer newProcessModelVersionId) {
        this.processModelVersionId = newProcessModelVersionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    public ProcessBranch getProcessBranch() {
        return this.processBranch;
    }

    public void setProcessBranch(final ProcessBranch newProcessBranches) {
        this.processBranch = newProcessBranches;
    }


    @Column(name = "root_fragment_version_id", length = 40)
    public String getRootFragmentVersionId() {
        return this.rootFragmentVersionId;
    }

    public void setRootFragmentVersionId(final String newRootFragmentVersionId) {
        this.rootFragmentVersionId = newRootFragmentVersionId;
    }


    @Column(name = "version_number")
    public Integer getVersionNumber() {
        return this.versionNumber;
    }

    public void setVersionNumber(final Integer newVersionNumber) {
        this.versionNumber = newVersionNumber;
    }


    @Column(name = "version_name", length = 200)
    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(final String newVersionName) {
        this.versionName = newVersionName;
    }


    @Column(name = "change_propagation")
    public Integer getChangePropagation() {
        return this.changePropagation;
    }

    public void setChangePropagation(final Integer newChangePropagation) {
        this.changePropagation = newChangePropagation;
    }


    @Column(name = "lock_status")
    public Integer getLockStatus() {
        return this.lockStatus;
    }

    public void setLockStatus(final Integer newLockStatus) {
        this.lockStatus = newLockStatus;
    }


    @Column(name = "num_nodes")
    public Integer getNumVertices() {
        return this.numVertices;
    }

    public void setNumVertices(final Integer newNumVertices) {
        this.numVertices = newNumVertices;
    }


    @Column(name = "num_edges")
    public Integer getNumEdges() {
        return this.numEdges;
    }

    public void setNumEdges(final Integer newNumEdges) {
        this.numEdges = newNumEdges;
    }


    @OneToMany(fetch=FetchType.LAZY, mappedBy="processModelVersion")
    public Set<Native> getNatives() {
        return this.natives;
    }

    public void setNatives(Set<Native> natives) {
        this.natives = natives;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subVersion")
    public Set<Node> getParentProcesses() {
        return this.parentProcesses;
    }

    public void setParentProcesses(final Set<Node> newParentProcesses) {
        this.parentProcesses = newParentProcesses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "processModelVersion")
    public Set<ProcessFragmentMap> getProcessFragmentMaps() {
        return this.processFragmentMaps;
    }

    public void setProcessFragmentMaps(final Set<ProcessFragmentMap> newProcessFragmentMaps) {
        this.processFragmentMaps = newProcessFragmentMaps;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "processModelVersionsByCurrentProcessModelVersionId")
    public Set<ProcessBranch> getProcessBranchesForCurrentProcessModelVersionId() {
        return this.processBranchesForCurrentProcessModelVersionId;
    }

    public void setProcessBranchesForCurrentProcessModelVersionId(final Set<ProcessBranch> newCurrentIds) {
        this.processBranchesForCurrentProcessModelVersionId = newCurrentIds;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "processModelVersionsBySourceProcessModelVersionId")
    public Set<ProcessBranch> getProcessBranchesForSourceProcessModelVersionId() {
        return this.processBranchesForSourceProcessModelVersionId;
    }

    public void setProcessBranchesForSourceProcessModelVersionId(final Set<ProcessBranch> newSourceIds) {
        this.processBranchesForSourceProcessModelVersionId = newSourceIds;
    }



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "processModelVersion")
    public Set<ProcessModelAttribute> getProcessModelAttributes() {
        return this.processModelAttributes;
    }

    public void setProcessModelAttributes(Set<ProcessModelAttribute> processModelAttributes) {
        this.processModelAttributes = processModelAttributes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "processModelVersion")
    public Set<ObjectType> getObjectTypes() {
        return this.objectTypes;
    }

    public void setObjectTypes(Set<ObjectType> objectTypes) {
        this.objectTypes = objectTypes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "processModelVersion")
    public Set<ResourceType> getResourceTypes() {
        return this.resourceTypes;
    }

    public void setResourceTypes(Set<ResourceType> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

}


