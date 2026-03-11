import {
  Avatar,
  Menu,
  MenuItem,
  Typography,
  Box,
  Divider,
  Button
} from "@mui/material";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { logout } from "../features/auth/authSlice";
import { useNavigate } from "react-router-dom";

export default function ProfileMenu() {

  const user = JSON.parse(localStorage.getItem("user"));
  const role = localStorage.getItem("role");

  const [anchorEl,setAnchorEl] = useState(null);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    dispatch(logout());
    navigate("/", { replace:true });
  };

  const getAvatarIcon = () => {

    if(role === "ADMIN") return "♜"; // chess rook
    if(role === "FACULTY") return "🧑‍🏫";
    return "👤";
  };

  return (
    <>

      <Avatar
        sx={{cursor:"pointer"}}
        onClick={(e)=>setAnchorEl(e.currentTarget)}
      >
        {getAvatarIcon()}
      </Avatar>

      <Menu
        anchorEl={anchorEl}
        open={Boolean(anchorEl)}
        onClose={()=>setAnchorEl(null)}
      >

        <Box sx={{px:2,py:1}}>

          <Typography fontWeight="bold">
            {user?.name}
          </Typography>

          <Typography variant="body2" color="text.secondary">
            {user?.email}
          </Typography>

        </Box>

        <Divider/>

        <MenuItem>Profile</MenuItem>
        <MenuItem>Settings</MenuItem>

        <Divider/>

        <MenuItem>

          <Button
            fullWidth
            color="error"
            onClick={handleLogout}
          >
            Logout
          </Button>

        </MenuItem>

      </Menu>

    </>
  );
}