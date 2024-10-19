import React, { useState } from "react";

const EvaluateRuleForm = () => {
  const [ruleName, setRuleName] = useState("");
  const [data, setData] = useState("");
  const [result, setResult] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await fetch("/api/rules/evaluate_rule", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ ast: ruleName, data: JSON.parse(data) }),
    });
    const result = await response.json();
    setResult(JSON.stringify(result, null, 2));
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Rule Name:</label>
          <input
            type="text"
            value={ruleName}
            onChange={(e) => setRuleName(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Data JSON:</label>
          <textarea
            value={data}
            onChange={(e) => setData(e.target.value)}
            required
          />
        </div>
        <button type="submit">Evaluate Rule</button>
      </form>
      <pre>{result}</pre>
    </div>
  );
};

export default EvaluateRuleForm;
