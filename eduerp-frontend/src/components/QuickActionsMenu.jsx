import { Menu, MenuItem } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function QuickActionsMenu({ anchorEl, open, onClose }) {

  const navigate = useNavigate();

  const go = (path) => {
    onClose();
    navigate(path);
  };

  return (
    <Menu anchorEl={anchorEl} open={open} onClose={onClose}>

      <MenuItem onClick={() => go("/admin/add-student")}>
        Add Student
      </MenuItem>

      <MenuItem onClick={() => go("/admin/add-faculty")}>
        Add Faculty
      </MenuItem>

      <MenuItem onClick={() => go("/admin/add-course")}>
        Add Course
      </MenuItem>

      <MenuItem onClick={() => go("/admin/revenue")}>
        View Revenue
      </MenuItem>

    </Menu>
  );
}