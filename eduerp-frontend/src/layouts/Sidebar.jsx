import {
  Box,
  Typography,
  List,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Drawer,
} from "@mui/material";
import { useLocation, useNavigate } from "react-router-dom";
import { menuConfig } from "../config/menuConfig";

export default function Sidebar({ mobileOpen, toggleSidebar, isMobile }) {
  const navigate = useNavigate();
  const location = useLocation();
  const role = localStorage.getItem("role");

  const menu = menuConfig[role] || [];

  const content = (
    <Box
      sx={{
        width: 250,
        bgcolor: "#4b2dbf",
        color: "#fff",
        // height: "100vh",
        height: "100vh",
        position: "sticky",
        top: 0,
        overflowY: "auto",
        p: 3,
      }}
    >
      <Typography variant="h5" fontWeight="bold" mb={4}>
        EduERP
      </Typography>

      <List>
        {menu.map((item) => {
          const isActive = location.pathname === item.path;
          const Icon = item.icon;

          return (
            <ListItemButton
              key={item.label}
              onClick={() => {
                navigate(item.path);
                if (isMobile) toggleSidebar();
              }}
              sx={{
                borderRadius: 2,
                mb: 1,
                bgcolor: isActive ? "rgba(255,255,255,0.2)" : "transparent",
                "&:hover": {
                  bgcolor: "rgba(255,255,255,0.15)",
                },
              }}
            >
              <ListItemIcon sx={{ color: "#fff", minWidth: 40 }}>
                <Icon />
              </ListItemIcon>

              <ListItemText primary={item.label} />
            </ListItemButton>
          );
        })}
      </List>
    </Box>
  );

  // Mobile → Drawer
  if (isMobile) {
    return (
      <Drawer open={mobileOpen} onClose={toggleSidebar} variant="temporary">
        {content}
      </Drawer>
    );
  }

  // Desktop → Permanent Sidebar
  // return (
  //   <Box sx={{ width: 250}}>
  //     {content}
  //   </Box>
  // );
  return content;
}
