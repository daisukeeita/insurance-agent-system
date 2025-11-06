// package com.acolyptos.insurance.domain.agent;
//
// import jakarta.validation.constraints.NotBlank;
//
// public class AgentLoginRequest {
//
//   @NotBlank(message = "Agent's username is required for logging in.")
//   private String username;
//
//   @NotBlank(message = "Agent's password is required for logging in.")
//   private String password;
//
//   protected AgentLoginRequest() {}
//
//   public AgentLoginRequest(final String username, final String password) {
//     this.username = username;
//     this.password = password;
//   }
//
//   public void setUsername(String username) {
//     this.username = username;
//   }
//
//   public void setPassword(String password) {
//     this.password = password;
//   }
//
//   public String getUsername() {
//     return username;
//   }
//
//   public String getPassword() {
//     return password;
//   }
//
//   @Override
//   public String toString() {
//     return "AgentLoginRequest {\n\tusername: " + username + ",\n\tpassword: " + password + "\n}";
//   }
// }
