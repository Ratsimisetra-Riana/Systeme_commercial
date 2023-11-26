import React from 'react';

export default class BonCommandeDetail extends React.Component {
  constructor(props) {    
    super(props);    
    this.state = {
      data: { 'idVarianteArticle': 0, 'quantiteVoulu': 0,'prixUnitaire':0,'tva':0,'prixUnitaireTtc':0 ,'varArticles':props.varArticle},
    };
  }

  handleInputChange = (e) => {
    console.log("change");
    let newData = { ...this.state.data };
    newData[e.target.name] = e.target.value;
    console.log(newData[e.target.name]);
    this.setState({ data: newData });
  }

  render() {
    return (
      <tr>
        <td id={this.props.id}>
          <select
            name="idVarianteArticle"
            type="number"
            onChange={this.handleInputChange}
            value={this.state.data['idVarianteArticle']}
          >
          {this.state.data.varArticles.map((item, index) => (
            <option key={index} value={item && item.idVarianteArticle}>
              {item && item.description}
            </option>
          ))}
          </select>
        </td>
        <td>
          <input
            name="quantiteVoulu"
            type="number"
            onChange={this.handleInputChange}
            value={this.state.data['quantiteVoulu']}
          />
        </td>
        <td>
          <input
            name="prixUnitaire"
            type="number"
            onChange={this.handleInputChange}
            value={this.state.data['prixUnitaire']}
          />
        </td>
        <td>
          <input
            name="tva"
            type="number"
            onChange={this.handleInputChange}
            value={this.state.data['tva']}
          />
        </td>
        <td>
          <input
            name="prixUnitaireTtc"
            type="number"
            onChange={this.handleInputChange}
            value={this.state.data['prixUnitaireTtc']}
          />
        </td>
      </tr>
    );
  }
}
