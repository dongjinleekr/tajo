/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 */
package org.apache.tajo.engine.planner.logical;

import com.google.gson.annotations.Expose;


public abstract class UnaryNode extends LogicalNode implements Cloneable {
	@Expose LogicalNode child;
	
	public UnaryNode() {
		super();
	}
	
	/**
	 * @param type
	 */
	public UnaryNode(NodeType type) {
		super(type);
	}
	
	public void setChild(LogicalNode subNode) {
		this.child = subNode;
	}
	
	public LogicalNode getChild() {
		return this.child;
	}
	
	@Override
  public Object clone() throws CloneNotSupportedException {
	  UnaryNode unary = (UnaryNode) super.clone();
	  unary.child = (LogicalNode) (child == null ? null : child.clone());
	  
	  return unary;
	}
	
	public void preOrder(LogicalNodeVisitor visitor) {
	  visitor.visit(this);
	  child.preOrder(visitor);
  }
	
	public void postOrder(LogicalNodeVisitor visitor) {
	  child.postOrder(visitor);
	  visitor.visit(this);
	}
}