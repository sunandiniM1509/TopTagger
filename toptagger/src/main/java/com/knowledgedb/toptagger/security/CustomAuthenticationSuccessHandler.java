package com.knowledgedb.toptagger.security;

import com.knowledgedb.toptagger.entity.Role;
import com.knowledgedb.toptagger.entity.User;
import com.knowledgedb.toptagger.entity.Users_Roles;
import com.knowledgedb.toptagger.service.UserService;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomAuthenticationSuccessHandler
  implements AuthenticationSuccessHandler {

  @Autowired
  private UserService userService;

  @Autowired
  private EntityManager entityManager;


  @Override
  @Transactional
  public void onAuthenticationSuccess(
    HttpServletRequest request,
    HttpServletResponse response,
    Authentication authentication
  )
    throws IOException, ServletException {
    String userName = authentication.getName();
    User theUser = userService.findByUserName(userName);
    HttpSession session = request.getSession();
    session.setAttribute("user", theUser);
    int uid = theUser.getId();
    Session currentSession = entityManager.unwrap(Session.class);
    Query<Users_Roles> theQuery = currentSession.createQuery(
      "from Users_Roles where user_id=:uid",
      Users_Roles.class
    );
    theQuery.setParameter("uid", uid);

    Users_Roles theUserRole = null;
    try {
      theUserRole = theQuery.getSingleResult();
    } catch (Exception e) {
      theUserRole = null;
    }

    Collection<Role> role = theUser.getRoles();

    Role roles = new Role();
      String fieldValue=null;

	  Role[] user_role= role.toArray(new Role[0]);

	  Role data = user_role[0];
      String val = String.valueOf(data);
      fieldValue = val.replaceFirst(".*'(.*?)'.*", "$1");

      if (fieldValue.equals("ROLE_ADMIN")) response.sendRedirect(
      request.getContextPath() + "/admin"
    ); else response.sendRedirect(request.getContextPath() + "/home");
  }
}
