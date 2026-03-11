import BaseLayout from "./BaseLayout";

export default function AdminLayout({ children }) {
  return <BaseLayout role="ADMIN">{children}</BaseLayout>;
}