// public void save() {
//   try(Connection con = DB.sql2o.open()) {
//     String sql = "INSERT INTO tasks (description) VALUES (:description)";
//     con.createQuery(sql)
//       .addParameter("description", description)
//       .executeUpdate();
//   }
