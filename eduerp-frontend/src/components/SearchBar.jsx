import { useState } from "react";
import { Box,InputBase,Paper,List,ListItem } from "@mui/material";

export default function SearchBar({data}){

 const [query,setQuery] = useState("");

 const results = data.filter(item =>
   item.toLowerCase().includes(query.toLowerCase())
 );

 return(

  <Box>

   <InputBase
    placeholder="Search..."
    value={query}
    onChange={(e)=>setQuery(e.target.value)}
   />

   {query && (

    <Paper>

     <List>

      {results.map((r,i)=>(
        <ListItem key={i}>{r}</ListItem>
      ))}

     </List>

    </Paper>

   )}

  </Box>

 );
}