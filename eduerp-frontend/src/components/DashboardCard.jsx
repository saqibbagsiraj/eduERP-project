import { Card, CardContent, Typography, Box } from "@mui/material";

export default function DashboardCard({ title, value, icon, color }) {
  return (
    <Card
      sx={{
        borderRadius: 3,
        boxShadow: 2,
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        p: 2,
        position: "relative",
        overflow: "hidden",
        transition: "all 0.3s ease",
        cursor: "pointer",

        "&:hover": {
          transform: "translateY(-6px)",
          boxShadow: 6
        }
      }}
    >
      {/* Accent bar */}
      <Box
        sx={{
          position: "absolute",
          left: 0,
          top: 0,
          bottom: 0,
          width: 6,
          bgcolor: color
        }}
      />

      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {title}
        </Typography>

        <Typography variant="h4" fontWeight="bold">
          {value}
        </Typography>
      </CardContent>

      <Box
        sx={{
          bgcolor: color,
          color: "#fff",
          p: 2,
          borderRadius: 2,
          transition: "0.3s",
          "&:hover": {
            transform: "scale(1.1)"
          }
        }}
      >
        {icon}
      </Box>
    </Card>
  );
}