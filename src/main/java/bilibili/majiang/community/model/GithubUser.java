package bilibili.majiang.community.model;

public class GithubUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GITHUBUSER.ID
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GITHUBUSER.ACCOUNT_ID
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GITHUBUSER.NAME
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GITHUBUSER.TOKEN
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GITHUBUSER.GMT_CREATED
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    private Long gmtCreated;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GITHUBUSER.GMT_MODIFIED
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GITHUBUSER.AVATAR_URL
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    private String avatarUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GITHUBUSER.ID
     *
     * @return the value of GITHUBUSER.ID
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GITHUBUSER.ID
     *
     * @param id the value for GITHUBUSER.ID
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GITHUBUSER.ACCOUNT_ID
     *
     * @return the value of GITHUBUSER.ACCOUNT_ID
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GITHUBUSER.ACCOUNT_ID
     *
     * @param accountId the value for GITHUBUSER.ACCOUNT_ID
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GITHUBUSER.NAME
     *
     * @return the value of GITHUBUSER.NAME
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GITHUBUSER.NAME
     *
     * @param name the value for GITHUBUSER.NAME
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GITHUBUSER.TOKEN
     *
     * @return the value of GITHUBUSER.TOKEN
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GITHUBUSER.TOKEN
     *
     * @param token the value for GITHUBUSER.TOKEN
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GITHUBUSER.GMT_CREATED
     *
     * @return the value of GITHUBUSER.GMT_CREATED
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public Long getGmtCreated() {
        return gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GITHUBUSER.GMT_CREATED
     *
     * @param gmtCreated the value for GITHUBUSER.GMT_CREATED
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public void setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GITHUBUSER.GMT_MODIFIED
     *
     * @return the value of GITHUBUSER.GMT_MODIFIED
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GITHUBUSER.GMT_MODIFIED
     *
     * @param gmtModified the value for GITHUBUSER.GMT_MODIFIED
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GITHUBUSER.AVATAR_URL
     *
     * @return the value of GITHUBUSER.AVATAR_URL
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GITHUBUSER.AVATAR_URL
     *
     * @param avatarUrl the value for GITHUBUSER.AVATAR_URL
     *
     * @mbg.generated Thu Apr 01 22:43:02 CST 2021
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}