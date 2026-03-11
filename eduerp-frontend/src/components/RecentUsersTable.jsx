import { DataGrid } from "@mui/x-data-grid";
import axios from "../services/axiosInstance";
import { useEffect,useState } from "react";

export default function RecentUsersTable(){

 const [rows,setRows]=useState([]);

 useEffect(()=>{

  axios.get("/admin/recent-users")
    .then(res=>setRows(res.data))

 },[])

 const columns=[
  {field:"name",headerName:"Name",flex:1},
  {field:"email",headerName:"Email",flex:1},
  {field:"role",headerName:"Role",flex:1},
  {field:"createdAt",headerName:"Joined",flex:1}
 ]

 return (
  <DataGrid
   rows={rows}
   columns={columns}
   autoHeight
   getRowId={(row)=>row.id}
  />
 )
}