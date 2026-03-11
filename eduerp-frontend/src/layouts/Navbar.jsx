import {
  Box,
  Typography,
  IconButton,
  InputBase,
  Avatar,
  Menu,
  MenuItem,
  Divider,
  Paper,
  List,
  ListItemButton,
  ListItemText
} from "@mui/material";

import MenuIcon from "@mui/icons-material/Menu";
import NotificationsIcon from "@mui/icons-material/Notifications";
import AddIcon from "@mui/icons-material/Add";
import SearchIcon from "@mui/icons-material/Search";

import { useDispatch } from "react-redux";
import { logout } from "../features/auth/authSlice";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

import axios from "../services/axiosInstance";
import QuickActionsMenu from "../components/QuickActionsMenu";

export default function Navbar({ toggleSidebar, isMobile }) {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const user = JSON.parse(localStorage.getItem("user"));
  const role = localStorage.getItem("role");

  const [quickAnchor, setQuickAnchor] = useState(null);
  const [profileAnchor, setProfileAnchor] = useState(null);

  const [query, setQuery] = useState("");
  const [results, setResults] = useState([]);

  /* Quick actions */

  const openQuickActions = (e) => setQuickAnchor(e.currentTarget);
  const closeQuickActions = () => setQuickAnchor(null);

  /* Profile */

  const openProfile = (e) => setProfileAnchor(e.currentTarget);
  const closeProfile = () => setProfileAnchor(null);

  const handleLogout = () => {
    dispatch(logout());
    navigate("/", { replace: true });
  };

  /* Avatar icon */

  const getAvatarIcon = () => {
    if (role === "ADMIN") return "♜";
    if (role === "FACULTY") return "🧑‍🏫";
    return "👤";
  };

  /* GLOBAL SEARCH */

  const handleSearch = async (value) => {

    setQuery(value);

    if (value.length === 0) {
      setResults([]);
      return;
    }

    try {

      const res = await axios.get(`/admin/search?q=${value}`);

      setResults(res.data);

    } catch (err) {

      console.error("Search error", err);

    }
  };

  /* Navigate to correct page */

  const handleResultClick = (item) => {

    setResults([]);
    setQuery("");

    if (item.type === "student")
      navigate(`/admin/students/${item.id}`);

    if (item.type === "faculty")
      navigate(`/admin/faculty/${item.id}`);

    if (item.type === "course")
      navigate(`/admin/courses/${item.id}`);

  };

  return (
    <Box
      sx={{
        height: 64,
        bgcolor: "#fff",
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        px: 3,
        boxShadow: 1,
        position: "relative"
      }}
    >

      {/* LEFT SECTION */}

      <Box sx={{ display: "flex", alignItems: "center", gap: 2 }}>

        {isMobile && (
          <IconButton onClick={toggleSidebar}>
            <MenuIcon />
          </IconButton>
        )}

        {isMobile && (
          <Typography variant="h6" fontWeight="bold">
            EduERP
          </Typography>
        )}

        {/* SEARCH BAR */}

        {!isMobile && (
          <Box sx={{ position: "relative" }}>

            <Box
              sx={{
                display: "flex",
                alignItems: "center",
                bgcolor: "#f1f3f4",
                px: 2,
                borderRadius: 2,
                width: 350
              }}
            >

              <SearchIcon sx={{ mr: 1 }} />

              <InputBase
                placeholder="Search students, faculty, courses..."
                value={query}
                onChange={(e) => handleSearch(e.target.value)}
                sx={{ width: "100%" }}
              />

            </Box>

            {/* SEARCH RESULTS */}

            {results.length > 0 && (

              <Paper
                sx={{
                  position: "absolute",
                  width: 350,
                  mt: 1,
                  zIndex: 1000
                }}
              >

                <List>

                  {results.map((item) => (

                    <ListItemButton
                      key={item.id}
                      onClick={() => handleResultClick(item)}
                    >

                      <ListItemText
                        primary={item.name}
                        secondary={item.type}
                      />

                    </ListItemButton>

                  ))}

                </List>

              </Paper>

            )}

          </Box>
        )}

      </Box>

      {/* RIGHT SECTION */}

      <Box sx={{ display: "flex", alignItems: "center", gap: 2 }}>

        {/* QUICK ACTION */}

        <IconButton onClick={openQuickActions}>
          <AddIcon />
        </IconButton>

        <QuickActionsMenu
          anchorEl={quickAnchor}
          open={Boolean(quickAnchor)}
          onClose={closeQuickActions}
        />

        {/* NOTIFICATIONS */}

        <IconButton>
          <NotificationsIcon />
        </IconButton>

        {/* PROFILE */}

        <Avatar
          sx={{ bgcolor: "#4b2dbf", cursor: "pointer" }}
          onClick={openProfile}
        >
          {getAvatarIcon()}
        </Avatar>

        <Menu
          anchorEl={profileAnchor}
          open={Boolean(profileAnchor)}
          onClose={closeProfile}
          anchorOrigin={{
            vertical: "bottom",
            horizontal: "right"
          }}
        >

          <Box sx={{ px: 2, py: 1 }}>

            <Typography fontWeight="bold">
              {user?.name}
            </Typography>

            <Typography variant="body2" color="text.secondary">
              {user?.email}
            </Typography>

            <Typography variant="caption">
              Role: {role}
            </Typography>

          </Box>

          <Divider />

          <MenuItem onClick={() => navigate("/profile")}>
            Profile
          </MenuItem>

          <MenuItem>
            Settings
          </MenuItem>

          <Divider />

          <MenuItem
            onClick={handleLogout}
            sx={{ color: "red", fontWeight: "bold" }}
          >
            Logout
          </MenuItem>

        </Menu>

      </Box>

    </Box>
  );
}