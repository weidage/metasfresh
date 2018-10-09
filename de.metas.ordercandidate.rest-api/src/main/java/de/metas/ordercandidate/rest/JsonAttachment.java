package de.metas.ordercandidate.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.metas.attachments.AttachmentEntry;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/*
 * #%L
 * de.metas.business
 * %%
 * Copyright (C) 2018 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@ApiModel("Describes a data attachment within metasfresh")
public class JsonAttachment
{
	@ApiModelProperty( //
			allowEmptyValue = false, //
			value = "Reference in terms of the external system. Can reference multiple records (e.g. multiple order line candidates)\n"
					+ "To be used in conjunktion with <code>dataSourceName</code>")
	String externalReference;

	@ApiModelProperty( //
			allowEmptyValue = false, //
			value = "Internal name of the <code>AD_InputDataSource</code> record that tells where this attachment came from.\n"
					+ "To be used in conjunktion with <code>externalReference</code>")
	String dataSourceName;

	@ApiModelProperty( //
			allowEmptyValue = false, //
			value = "ID assigned to the attachment data by metasfresh")
	String attachmentId;

	AttachmentEntry.Type type;

	String filename;

	String contentType;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final String url;

	@JsonCreator
	@Builder(toBuilder = true)
	private JsonAttachment(
			@JsonProperty("externalReference") @NonNull final String externalReference,
			@JsonProperty("dataSourceName") @NonNull final String dataSourceName,
			@JsonProperty("attachmentId") @NonNull final String attachmentId,
			@JsonProperty("type") final @NonNull AttachmentEntry.Type type,
			@JsonProperty("filename") @NonNull final String filename,
			@JsonProperty("contentType") @NonNull final String contentType,
			@JsonProperty("url") final String url)
	{
		this.externalReference = externalReference;
		this.dataSourceName = dataSourceName;
		this.attachmentId = attachmentId;
		this.type = type;
		this.filename = filename;
		this.contentType = contentType;
		this.url = url;
	}

	/** Used by adapters where the data source name is a constant and would only be a distraction for the adapter's invoker. */
	public JsonAttachment withoutDataSource()
	{
		return toBuilder()
				.dataSourceName(null)
				.build();
	}
}
