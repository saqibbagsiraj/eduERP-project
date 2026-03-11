import { Grid } from "@mui/material";
import authImage from "../../assets/login_signup.jpeg";

export default function AuthLayout({ left, right }) {
  return (
    <Grid container sx={{ height: "100vh", width: "100vw", overflow: "hidden" }}>

      {/* Branding */}
      <Grid
        item
        xs={12}
        md={3}
        sx={{
          background: "linear-gradient(135deg,#4b2dbf,#6c4cff)",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          color: "#fff",
          p: 4
        }}
      >
        {left}
      </Grid>

      {/* Form */}
      <Grid
        item
        xs={12}
        md={4}
        sx={{
          background: "#f5f6fa",
          display: "flex",
          alignItems: "center",
          justifyContent: "center"
        }}
      >
        {right}
      </Grid>

      {/* Illustration */}
      <Grid
        item
        xs={false}
        md={5}
        sx={{
          backgroundImage: `url("${authImage}")`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          backgroundRepeat: "no-repeat"
        }}
      />

    </Grid>
  );
}