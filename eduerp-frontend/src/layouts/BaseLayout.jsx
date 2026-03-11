import { Box, useMediaQuery } from "@mui/material";
import { useTheme } from "@mui/material/styles";
import { useState } from "react";
import Sidebar from "./Sidebar";
import Navbar from "./Navbar";

export default function BaseLayout({ children }) {
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("md"));

  const [mobileOpen, setMobileOpen] = useState(false);

  const toggleSidebar = () => {
    setMobileOpen(!mobileOpen);
  };

  return (
    <Box sx={{ display: "flex", minHeight: "100vh" }}>
      {/* Desktop Sidebar */}
      {!isMobile && <Sidebar />}

      {/* Mobile Sidebar Drawer */}
      {isMobile && (
        <Sidebar
          mobileOpen={mobileOpen}
          toggleSidebar={toggleSidebar}
          isMobile
        />
      )}

      <Box
        sx={{
          flexGrow: 1,
          bgcolor: "#f4f6f9",
          display: "flex",
          flexDirection: "column",
        }}
      >
        <Navbar toggleSidebar={toggleSidebar} isMobile={isMobile} />

        {/* <Box sx={{ flexGrow: 1, p: 3, overflowY: "auto" }}>
          {children}
        </Box> */}
        <Box
          sx={{
            flexGrow: 1,
            overflowY: "auto",
            bgcolor: "#f4f6f9",
          }}
        >
          {children}
        </Box>
      </Box>
    </Box>
  );
}
