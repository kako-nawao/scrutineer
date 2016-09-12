package com.aconex.scrutineer;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

// CHECKSTYLE:OFF This is the standard JCommander pattern
@Parameters(separators = "=")
public class ScrutineerCommandLineOptions {
    @Parameter(names = "--hostName", description = "ElasticSearch hostname identifier")
    public String hostName;

    @Parameter(names = "--portNumber", description = "ElasticSearch port identifier")
    public int portNumber = 9300;

    @Parameter(names = "--clusterName", description = "ElasticSearch cluster name identifier")
    public String clusterName;

    @Parameter(names = "--indexName", description = "ElasticSearch index name to Verify", required = true)
    public String indexName;

    @Parameter(names = "--query", description = "ElasticSearch query to create Secondary stream.  Not required to be ordered")
    public String query = "*";

    @Parameter(names = "--versionField", description = "ElasticSearch version field if in document source")
    public String versionField = "";

    @Parameter(names = "--jdbcDriverClass", description = "FQN of the JDBC Driver class", required = true)
    public String jdbcDriverClass;

    @Parameter(names = "--jdbcURL", description = "JDBC URL of the Connection of the Primary source", required = true)
    public String jdbcURL;

    @Parameter(names = "--jdbcUser", description = "JDBC Username", required = true)
    public String jdbcUser;

    @Parameter(names = "--jdbcPassword", description = "JDBC Password")
    public String jdbcPassword;

    @Parameter(names = "--sql", description = "SQL used to create Primary stream, which should return results in _lexicographical_ order", required = true)
    public String sql;

    @Parameter(names = "--numeric", description = "JDBC query is sorted numerically")
    public boolean numeric = false;

    @Parameter(names = "--versions-as-timestamps", description = "Assumes Version values are timestamps and are printed out in ISO8601 date/time format for convenience")
    public boolean versionsAsTimestamps = false;

    @Parameter(names = "--ignore-timestamps-during-run", description = "Will suppress any Version Mismatch warnings whose timestamps are after the start of a Scrutineer run (implies use of --versionsAsTimestamps)")
    public boolean ignoreTimestampsDuringRun = false;
}
// CHECKSTYLE:ON