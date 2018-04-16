package hello.pojo;

public class ChannelData {

    private Tenant tenant;

    private String teamsChannelId;

    private String teamsTeamId;

    private Team team;

    private Channel channel;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getTeamsChannelId() {
        return teamsChannelId;
    }

    public void setTeamsChannelId(String teamsChannelId) {
        this.teamsChannelId = teamsChannelId;
    }

    public String getTeamsTeamId() {
        return teamsTeamId;
    }

    public void setTeamsTeamId(String teamsTeamId) {
        this.teamsTeamId = teamsTeamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "ChannelData [tenant = " + tenant + ", teamsChannelId = " + teamsChannelId + ", teamsTeamId = " + teamsTeamId + ", team = " + team + ", channel = " + channel + "]";
    }
}
