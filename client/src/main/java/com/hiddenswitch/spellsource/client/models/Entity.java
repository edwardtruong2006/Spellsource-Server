/**
 * Hidden Switch Spellsource API
 * The Spellsource API for matchmaking, user accounts, collections management and more
 *
 * OpenAPI spec version: 1.0.1
 * Contact: benjamin.s.berman@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.hiddenswitch.spellsource.client.models;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import com.hiddenswitch.spellsource.client.models.EntityState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * Entity
 */

public class Entity  implements Serializable {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("cardId")
  private String cardId = null;

  /**
   * Broad categories describing this entity and how it should be rendered. 
   */
  public enum EntityTypeEnum {
    @SerializedName("PLAYER")
    PLAYER("PLAYER"),
    
    @SerializedName("HERO")
    HERO("HERO"),
    
    @SerializedName("CARD")
    CARD("CARD"),
    
    @SerializedName("MINION")
    MINION("MINION"),
    
    @SerializedName("WEAPON")
    WEAPON("WEAPON"),
    
    @SerializedName("SECRET")
    SECRET("SECRET");

    private String value;

    EntityTypeEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("entityType")
  private EntityTypeEnum entityType = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("state")
  private EntityState state = null;

  public Entity id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * The entity's ID in the game.
   * @return id
  **/
  @ApiModelProperty(example = "null", required = true, value = "The entity's ID in the game.")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Entity cardId(String cardId) {
    this.cardId = cardId;
    return this;
  }

   /**
   * The entity's Card ID. When null, it typically should not be rendered.
   * @return cardId
  **/
  @ApiModelProperty(example = "null", value = "The entity's Card ID. When null, it typically should not be rendered.")
  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }

  public Entity entityType(EntityTypeEnum entityType) {
    this.entityType = entityType;
    return this;
  }

   /**
   * Broad categories describing this entity and how it should be rendered. 
   * @return entityType
  **/
  @ApiModelProperty(example = "null", value = "Broad categories describing this entity and how it should be rendered. ")
  public EntityTypeEnum getEntityType() {
    return entityType;
  }

  public void setEntityType(EntityTypeEnum entityType) {
    this.entityType = entityType;
  }

  public Entity name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The text that would go into the entity's name field. 
   * @return name
  **/
  @ApiModelProperty(example = "null", value = "The text that would go into the entity's name field. ")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Entity description(String description) {
    this.description = description;
    return this;
  }

   /**
   * The text that would go into the entity's description field. 
   * @return description
  **/
  @ApiModelProperty(example = "null", value = "The text that would go into the entity's description field. ")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Entity state(EntityState state) {
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(example = "null", value = "")
  public EntityState getState() {
    return state;
  }

  public void setState(EntityState state) {
    this.state = state;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Entity entity = (Entity) o;
    return Objects.equals(this.id, entity.id) &&
        Objects.equals(this.cardId, entity.cardId) &&
        Objects.equals(this.entityType, entity.entityType) &&
        Objects.equals(this.name, entity.name) &&
        Objects.equals(this.description, entity.description) &&
        Objects.equals(this.state, entity.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cardId, entityType, name, description, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Entity {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cardId: ").append(toIndentedString(cardId)).append("\n");
    sb.append("    entityType: ").append(toIndentedString(entityType)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
