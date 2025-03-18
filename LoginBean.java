import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@ManagedBean
@RequestScoped
public class LoginBean {

    private String username;
    private String password;

    @Inject
    private EntityManager em;

    public String login() {
        try {
            User user = (User) em
                    .createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return "home.xhtml"; // Weiterleitung zur Startseite nach erfolgreichem Login
        } catch (NoResultException e) {
            return "login.xhtml?faces-redirect=true&error=true"; // Fehlermeldung, wenn Login fehlschlägt
        }
    }

    // Getter und Setter für Username und Password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
