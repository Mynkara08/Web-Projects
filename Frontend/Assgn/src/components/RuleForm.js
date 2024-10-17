import React, { useState } from 'react';

const RuleForm = () => {
  const [ruleName, setRuleName] = useState('');
  const [ruleString, setRuleString] = useState('');
  const [result, setResult] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await fetch('/api/rules/create_rule', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ ruleName, ruleString }),
    });
    const data = await response.json();
    setResult(JSON.stringify(data, null, 2));
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
          <label>Rule:</label>
          <input
            type="text"
            value={ruleString}
            onChange={(e) => setRuleString(e.target.value)}
            required
          />
        </div>
        <button type="submit">Create Rule</button>
      </form>
      <pre>{result}</pre>
    </div>
  );
};

export default RuleForm;
