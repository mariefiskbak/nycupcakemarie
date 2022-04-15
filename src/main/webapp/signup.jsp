<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Opret bruger
    </jsp:attribute>

    <jsp:attribute name="footer">
        Opret bruger
    </jsp:attribute>

    <jsp:body>
        <h3 style="color: red">${requestScope.fejlTilIndex }</h3>

        <section>
            <div class="container">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-lg-12 col-xl-11">
                        <div class="card text-black" style="border-radius: 25px;">
                            <div class="card-body p-md-5">
                                <div class="row justify-content-center">
                                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                        <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Opret bruger</p>

                                        <form class="mx-1 mx-md-4" action="SignupServlet" method="post">
                                            <div class="row">
                                                <div class="col">
                                                    <div class="d-flex flex-row align-items-center mb-4">
                                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                        <div class="form-outline flex-fill mb-0">
                                                            <input type="text" id="firstname" class="form-control"
                                                                   name="firstname"/>
                                                            <label class="form-label" for="firstname">Fornavn</label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col">
                                                    <div class="d-flex flex-row align-items-center mb-4">
                                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                        <div class="form-outline flex-fill mb-0">
                                                            <input type="text" id="lastname" class="form-control"
                                                                   name="lastname"/>
                                                            <label class="form-label"
                                                                   for="lastname">Efternavn</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input type="number" id="phoneno" class="form-control"
                                                           name="phoneno"/>
                                                    <label class="form-label" for="phoneno">Telefonnummer</label>
                                                </div>
                                            </div>

                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input type="email" id="email" class="form-control"
                                                           name="email"/>
                                                    <label class="form-label" for="email">Email</label>
                                                </div>
                                            </div>

                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input type="password" id="password" class="form-control"
                                                           name="password"/>
                                                    <label class="form-label" for="password">Password</label>
                                                </div>
                                            </div>

                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input type="password" id="rppassword" class="form-control"
                                                           name="rppassword"/>
                                                    <label class="form-label" for="rppassword">Gentag
                                                        password</label>
                                                </div>
                                            </div>
                                            <div class="container signin">
                                                <p>Har du allerede en konto? <a href="login.jsp">Log ind</a></p>
                                            </div>

                                            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                                <button type="submit" class="btn btn-primary btn-lg">Opret konto
                                                </button>
                                            </div>

                                        </form>

                                    </div>
                                    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                                        <img src="${pageContext.request.contextPath}/images/signupcup.png"
                                             class="img-fluid" style="border-radius: 10px">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <%--  <section class="vh-100" style="background-color: #eee;">
              <div class="container h-100">
                  <div class="row d-flex justify-content-center align-items-center h-100">
                      <div class="col-lg-12 col-xl-11">
                          <div class="card text-black" style="border-radius: 25px;">
                              <div class="card-body p-md-5">
                                  <div class="row justify-content-center">
                                      <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                          <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                                          <form class="mx-1 mx-md-4">

                                              <div class="d-flex flex-row align-items-center mb-4">
                                                  <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                  <div class="form-outline flex-fill mb-0">
                                                      <input type="text" id="form3Example1c" class="form-control" />
                                                      <label class="form-label" for="form3Example1c">Your Name</label>
                                                  </div>
                                              </div>

                                              <div class="d-flex flex-row align-items-center mb-4">
                                                  <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                                  <div class="form-outline flex-fill mb-0">
                                                      <input type="email" id="form3Example3c" class="form-control" />
                                                      <label class="form-label" for="form3Example3c">Your Email</label>
                                                  </div>
                                              </div>

                                              <div class="d-flex flex-row align-items-center mb-4">
                                                  <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                                  <div class="form-outline flex-fill mb-0">
                                                      <input type="password" id="form3Example4c" class="form-control" />
                                                      <label class="form-label" for="form3Example4c">Password</label>
                                                  </div>
                                              </div>

                                              <div class="d-flex flex-row align-items-center mb-4">
                                                  <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                                  <div class="form-outline flex-fill mb-0">
                                                      <input type="password" id="form3Example4cd" class="form-control" />
                                                      <label class="form-label" for="form3Example4cd">Repeat your password</label>
                                                  </div>
                                              </div>

                                              <div class="form-check d-flex justify-content-center mb-5">
                                                  <input
                                                          class="form-check-input me-2"
                                                          type="checkbox"
                                                          value=""
                                                          id="form2Example3c"
                                                  />
                                                  <label class="form-check-label" for="form2Example3c">
                                                      I agree all statements in <a href="#!">Terms of service</a>
                                                  </label>
                                              </div>

                                              <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                                  <button type="button" class="btn btn-primary btn-lg">Register</button>
                                              </div>

                                          </form>

                                      </div>
                                      <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                          <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp" class="img-fluid" alt="Sample image">

                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </section>--%>


        <%--<form action="SignupServlet" class="">
            <div class="container">
                <h1>Opret bruger</h1>

                <label for="email"><b>Email</b></label>
                <input type="text" placeholder="Skriv Email" name="email" id="email" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Skriv Password" name="psw" id="psw" required>

                <label for="psw-repeat"><b>Gentag Password</b></label>
                <input type="password" placeholder="Gentag Password" name="psw-repeat" id="psw-repeat" required>

                <label for="firstname"><b>Fornavn</b></label>
                <input type="text" placeholder="Fornavn" name="firstname" id="firstname" required>

                <label for="surname"><b>Efternavn</b></label>
                <input type="text" placeholder="Efternavn" name="surname" id="surname" required>

                <label for="balance"><b>Balance</b></label>
                <input type="text" placeholder="Balance" name="balance" id="balance" required>

                <button type="submit" class="registerbtn">Opret konto</button>
            </div>

            <div class="container signin">
                <p>Har du allerede en konto? <a href="login.jsp">Log ind</a></p>
            </div>
        </form>--%>

    </jsp:body>

</t:pagetemplate>