import { TextField, Button, Typography, Paper, Alert } from "@mui/material";
import { motion } from "framer-motion";
import AuthLayout from "./AuthLayout";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { loginUser } from "./authSlice";

export default function LoginPage() {
  const { register, handleSubmit } = useForm();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const { loading, error } = useSelector((state) => state.auth);

  const onSubmit = async (data) => {
    try {
      const res = await dispatch(loginUser(data)).unwrap();

      if (res?.token) {
        const role = res.role; // ✅ FIXED

        if (role === "ADMIN") {
          navigate("/admin/dashboard", { replace: true });
        } else if (role === "FACULTY") {
          navigate("/faculty/dashboard", { replace: true });
        } else if (role === "STUDENT") {
          navigate("/student/dashboard", { replace: true });
        }
      }
    } catch (err) {
      console.error("Login Failed:", err);
    }
  };
  return (
    <AuthLayout
      left={
        <motion.div
          initial={{ opacity: 0, x: -40 }}
          animate={{ opacity: 1, x: 0 }}
          transition={{ duration: 0.6 }}
        >
          <Typography variant="h3" fontWeight="bold">
            EduERP
          </Typography>
          <Typography mt={2}>Smart Education Management</Typography>
        </motion.div>
      }
      right={
        <motion.div
          initial={{ opacity: 0, y: 40 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.6 }}
        >
          <Paper elevation={8} sx={{ p: 4, width: 320, borderRadius: 3 }}>
            <Typography variant="h5" mb={2}>
              Login
            </Typography>

            {error && <Alert severity="error">{error}</Alert>}

            <form onSubmit={handleSubmit(onSubmit)}>
              <TextField
                fullWidth
                label="Email"
                margin="normal"
                {...register("email")}
              />

              <TextField
                fullWidth
                label="Password"
                type="password"
                margin="normal"
                {...register("password")}
              />

              <Button
                fullWidth
                variant="contained"
                sx={{
                  mt: 2,
                  background: "linear-gradient(135deg,#4b2dbf,#6c4cff)",
                }}
                type="submit"
                disabled={loading}
              >
                {loading ? "Logging in..." : "Login"}
              </Button>

              <Typography mt={2} textAlign="center">
                Not registered yet?{" "}
                <Link
                  to="/signup"
                  style={{ color: "#4b2dbf", fontWeight: "bold" }}
                >
                  Signup
                </Link>
              </Typography>
            </form>
          </Paper>
        </motion.div>
      }
    />
  );
}
