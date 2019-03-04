public class ElectionResult {
    private double votes_dem;
    private double votes_gop;
    private double total_votes;
    private double per_dem;
    private double per_gop;
    private double diff;
    private double per_point_diff;
    private String state_abbr;
    private String county_name;

    public double getVotes_dem() {
        return votes_dem;
    }

    public void setVotes_dem(double votes_dem) {
        this.votes_dem = votes_dem;
    }

    public double getVotes_gop() {
        return votes_gop;
    }

    public void setVotes_gop(double votes_gop) {
        this.votes_gop = votes_gop;
    }

    public double getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(double total_votes) {
        this.total_votes = total_votes;
    }

    public double getPer_dem() {
        return per_dem;
    }

    public void setPer_dem(double per_dem) {
        this.per_dem = per_dem;
    }

    public double getPer_gop() {
        return per_gop;
    }

    public void setPer_gop(double per_gop) {
        this.per_gop = per_gop;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public double getPer_point_diff() {
        return per_point_diff;
    }

    public void setPer_point_diff(double per_point_diff) {
        this.per_point_diff = per_point_diff;
    }

    public String getState_abbr() {
        return state_abbr;
    }

    public void setState_abbr(String state_abbr) {
        this.state_abbr = state_abbr;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public int getCombined_fips() {
        return combined_fips;
    }

    public void setCombined_fips(int combined_fips) {
        this.combined_fips = combined_fips;
    }

    private int combined_fips;

    public ElectionResult(String[] singleLineOfData) {
        double votes_dem = Double.parseDouble(singleLineOfData[0]);
        double votes_gop = Double.parseDouble(singleLineOfData[0]);
        double total_votes = Double.parseDouble(singleLineOfData[0]);
        double per_dem = Double.parseDouble(singleLineOfData[0]);
        double per_gop = Double.parseDouble(singleLineOfData[0]);
        double diff = Double.parseDouble(singleLineOfData[0]);
        double per_point_diff = Double.parseDouble(singleLineOfData[0].replace("%", ""));
        String state_abbr = singleLineOfData[0];
        String county_name = singleLineOfData[0];
        int combined_fips = Integer.parseInt(singleLineOfData[0]);
    }

    public String toString() {
        return String.valueOf(votes_dem + " " + votes_gop + " " + total_votes + " " + per_dem + " " + per_gop + " " + diff + " " + per_point_diff + " " + state_abbr + " " + county_name + " " + combined_fips);
    }
}
