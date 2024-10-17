import React, { useState } from 'react';

const CombineRulesForm = () => {
  const [rules, setRules] = useState([{ id: 1, rule: '', operator: 'AND' }]);
  const [result, setResult] = useState('');

  const handleAddRule = () => {
    setRules([...rules, { id: rules.length + 1, rule: '', operator: 'AND' }]);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await fetch('/api/rules/combine_rules', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ rules }),
    });
    const data = await response.json();
    setResult(JSON.stringify(data, null, 2));
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        {rules.map((rule, index) => (
          <div key={rule.id} className="rule-container">
            <label>Rule {index + 1}:</label>
            <input
              type="text"
              value={rule.rule}
              onChange={(e) => {
                const newRules = [...rules];
                newRules[index].rule = e.target.value;
                setRules(newRules);
              }}
              required
            />
            <label>Operator: </label>
            <select
              value={rule.operator}
              onChange={(e) => {
                const newRules = [...rules];
                newRules[index].operator = e.target.value;
                setRules(newRules);
              }}
              className="dropdown"
            >
              <option value="AND">AND</option>
              <option value="OR">OR</option>
            </select>
          </div>
        ))}

        <div className="button-container">
          <button type="button" onClick={handleAddRule} className="btn">
            Add Another Rule
          </button>
          <button type="submit" className="btn">
            Combine Rules
          </button>
        </div>
      </form>

      <pre>{result}</pre>
    </div>
  );
};

export default CombineRulesForm;
