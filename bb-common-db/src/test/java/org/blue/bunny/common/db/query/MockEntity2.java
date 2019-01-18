package org.blue.bunny.common.db.query;

import org.blue.bunny.common.db.annotation.Column;
import org.blue.bunny.common.db.annotation.Entity;
import org.blue.bunny.common.db.annotation.Id;
import org.blue.bunny.common.db.annotation.Version;
import org.blue.bunny.common.db.generators.RandomIdGenerator;

@Entity(table = "mock2")
public class MockEntity2 {
    @Column(columnName = "id", columnLabel = "id")
    @Id(RandomIdGenerator.class)
    private String id;
    
    @Column(columnName = "version", columnLabel = "version")
    @Version
    private long version;
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setVersion(long version) {
        this.version = version;
    }
    
    public long getVersion() {
        return version;
    }
    
}