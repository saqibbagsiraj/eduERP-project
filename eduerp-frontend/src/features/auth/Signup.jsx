import { TextField, Button, Typography, Paper, MenuItem, Alert } from "@mui/material";
import { motion } from "framer-motion";
import AuthLayout from "./AuthLayout";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import axios from "../../services/axiosInstance";
import { useState } from "react";

export default function SignupPage() {

  const { register, handleSubmit } = useForm();
  const navigate = useNavigate();

  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const roleMap = {
    STUDENT: 1,
    FACULTY: 2,
    ADMIN: 3
  };

  const onSubmit = async (data) => {

    try {
      setLoading(true);
      setError(null);

      // 🔥 Convert role string → role object
      const payload = {
        name: data.name,
        email: data.email,
        password: data.password,
        role: {
          id: roleMap[data.role]
        }
      };

      await axios.post("/auth/register", payload);

      navigate("/");

    } catch (err) {
      setError(err.response?.data?.message || "Signup failed");
    } finally {
      setLoading(false);
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
          <Typography mt={2}>Join Smart Education Management</Typography>
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
              Signup
            </Typography>

            {error && <Alert severity="error">{error}</Alert>}

            <form onSubmit={handleSubmit(onSubmit)}>

              <TextField
                fullWidth
                label="Full Name"
                margin="normal"
                {...register("name")}
              />

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

              <TextField
                select
                fullWidth
                label="Role"
                margin="normal"
                defaultValue="STUDENT"
                {...register("role")}
              >
                <MenuItem value="STUDENT">Student</MenuItem>
                <MenuItem value="FACULTY">Faculty</MenuItem>
                <MenuItem value="ADMIN">Admin</MenuItem>
              </TextField>

              <Button
                fullWidth
                variant="contained"
                sx={{ mt: 2, background: "linear-gradient(135deg,#4b2dbf,#6c4cff)" }}
                type="submit"
                disabled={loading}
              >
                {loading ? "Signing up..." : "Signup"}
              </Button>

              <Typography mt={2} textAlign="center">
                Already have an account?{" "}
                <Link to="/" style={{ color: "#4b2dbf", fontWeight: "bold" }}>
                  Login
                </Link>
              </Typography>

            </form>
          </Paper>
        </motion.div>
      }
    />
  );
}