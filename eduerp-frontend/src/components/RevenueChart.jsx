import { Bar } from "react-chartjs-2";
import axios from "../services/axiosInstance";
import { useEffect, useState } from "react";

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Tooltip,
  Legend
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Tooltip,
  Legend
);

export default function RevenueChart(){

  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get("/admin/revenue-chart")
      .then(res => setData(res.data))
      .catch(err => console.error(err));
  }, []);

  const chartData = {
    labels: data.map(d => d.month),
    datasets: [
      {
        label: "Revenue",
        data: data.map(d => d.revenue),
        backgroundColor: "#00b894"
      }
    ]
  };

  return <Bar data={chartData} />;
}