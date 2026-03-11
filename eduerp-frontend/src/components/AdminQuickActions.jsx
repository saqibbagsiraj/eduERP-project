import { Button,Stack } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function AdminQuickActions(){

 const navigate = useNavigate();

 return (

  <Stack spacing={2}>

   <Button variant="contained" onClick={()=>navigate("/admin/add-student")}>
    Add Student
   </Button>

   <Button variant="contained" onClick={()=>navigate("/admin/add-faculty")}>
    Add Faculty
   </Button>

   <Button variant="contained" onClick={()=>navigate("/admin/add-course")}>
    Add Course
   </Button>

  </Stack>

 )
}