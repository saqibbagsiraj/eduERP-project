import { Line } from "react-chartjs-2";
import { useEffect, useState } from "react";
import axios from "../services/axiosInstance";

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Tooltip,
  Legend
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Tooltip,
  Legend
);

export default function StudentGrowthChart() {

  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get("/admin/student-growth")
      .then(res => setData(res.data))
      .catch(err => console.error(err));
  }, []);

  const chartData = {
    labels: data.map(d => d.month),
    datasets: [
      {
        label: "Students",
        data: data.map(d => d.count),
        borderColor: "#4b2dbf",
        backgroundColor: "rgba(75,45,191,0.2)",
        tension: 0.4
      }
    ]
  };

  return <Line data={chartData} />;
}