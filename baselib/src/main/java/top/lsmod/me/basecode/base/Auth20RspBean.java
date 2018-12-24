package top.lsmod.me.basecode.base;

import java.io.Serializable;
import java.util.List;

/**
 * Author:yanfulei
 * Date:2018/11/2
 * Email:yanfulei1990@gmail.com
 **/
public class Auth20RspBean implements Serializable {
    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;
    private String LoginUser;
    private AuthUserInfo userInfo;
    // 本地属性，是否离线登陆
    private boolean isOffLineLogin;

    public boolean isOffLineLogin() {
        return isOffLineLogin;
    }

    public void setOffLineLogin(boolean offLineLogin) {
        isOffLineLogin = offLineLogin;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getLoginUser() {
        return LoginUser;
    }

    public void setLoginUser(String loginUser) {
        LoginUser = loginUser;
    }

    public AuthUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(AuthUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static class AuthUserInfo implements Serializable{
        private int UserId;
        private String CompanyNo;
        private String UserName;
        private String Name;
        private String Email;
        private String Mobile;
        private int OrgType;
        private List<Roles> Roles;
        private DepInfo DepInfo;
        private CompanyInfo CompanyInfo;
//        private List<Menus> Menus;
        private int Status;

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int userId) {
            UserId = userId;
        }

        public String getCompanyNo() {
            return CompanyNo;
        }

        public void setCompanyNo(String companyNo) {
            CompanyNo = companyNo;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String mobile) {
            Mobile = mobile;
        }

        public int getOrgType() {
            return OrgType;
        }

        public void setOrgType(int orgType) {
            OrgType = orgType;
        }

        public List<Auth20RspBean.Roles> getRoles() {
            return Roles;
        }

        public void setRoles(List<Auth20RspBean.Roles> roles) {
            Roles = roles;
        }

        public Auth20RspBean.DepInfo getDepInfo() {
            return DepInfo;
        }

        public void setDepInfo(Auth20RspBean.DepInfo depInfo) {
            DepInfo = depInfo;
        }

        public Auth20RspBean.CompanyInfo getCompanyInfo() {
            return CompanyInfo;
        }

        public void setCompanyInfo(Auth20RspBean.CompanyInfo companyInfo) {
            CompanyInfo = companyInfo;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }
    }

    public class Roles implements Serializable {

        private int Id;
        private String Name;
        public void setId(int Id) {
            this.Id = Id;
        }
        public int getId() {
            return Id;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
        public String getName() {
            return Name;
        }

    }
    public class DepInfo implements Serializable {

        private int DepId;
        private String Name;
        private int Type;
        private int ParentId;

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int parentId) {
            ParentId = parentId;
        }

        public void setDepId(int DepId) {
            this.DepId = DepId;
        }
        public int getDepId() {
            return DepId;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
        public String getName() {
            return Name;
        }

        public void setType(int Type) {
            this.Type = Type;
        }
        public int getType() {
            return Type;
        }

    }
    public class CompanyInfo implements Serializable {

        private String CompanyNo;
        private String FullName;
        private String Name;
        private String Logo;
        private int OrgType;
        private String AgentNo;
        private int Status;
        private int ExpireTime;
        private int GroupId;
        private boolean SupportAgent;
        private boolean Expired;
        public void setCompanyNo(String CompanyNo) {
            this.CompanyNo = CompanyNo;
        }
        public String getCompanyNo() {
            return CompanyNo;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }
        public String getFullName() {
            return FullName;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
        public String getName() {
            return Name;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }
        public String getLogo() {
            return Logo;
        }

        public void setOrgType(int OrgType) {
            this.OrgType = OrgType;
        }
        public int getOrgType() {
            return OrgType;
        }

        public void setAgentNo(String AgentNo) {
            this.AgentNo = AgentNo;
        }
        public String getAgentNo() {
            return AgentNo;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }
        public int getStatus() {
            return Status;
        }

        public void setExpireTime(int ExpireTime) {
            this.ExpireTime = ExpireTime;
        }
        public int getExpireTime() {
            return ExpireTime;
        }

        public void setGroupId(int GroupId) {
            this.GroupId = GroupId;
        }
        public int getGroupId() {
            return GroupId;
        }

        public void setSupportAgent(boolean SupportAgent) {
            this.SupportAgent = SupportAgent;
        }
        public boolean getSupportAgent() {
            return SupportAgent;
        }

        public void setExpired(boolean Expired) {
            this.Expired = Expired;
        }
        public boolean getExpired() {
            return Expired;
        }

    }
}
